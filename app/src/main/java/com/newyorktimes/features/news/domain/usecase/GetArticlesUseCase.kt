package com.newyorktimes.features.news.domain.usecase

import com.newyorktimes.core.Resource
import com.newyorktimes.features.news.domain.model.Article
import com.newyorktimes.features.news.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteArticles = articleRepository.getRemoteArticles()

            articleRepository.saveArticles(remoteArticles)

            emit(Resource.Success(remoteArticles))
        } catch (e: HttpException) {
            handleLocalFallback(e.message())
        } catch (e: IOException) {
            handleLocalFallback("Network error: ${e.localizedMessage}")
        } catch (e: Exception) {
            emit(Resource.Error("Unexpected error: ${e.localizedMessage}"))
        }
    }

    private suspend fun FlowCollector<Resource<List<Article>>>.handleLocalFallback(
        errorMessage: String
    ) {
        val localArticles = articleRepository.getLocalArticles().firstOrNull()

        if (localArticles.isNullOrEmpty()) {
            emit(Resource.Error(errorMessage))
        } else {
            emit(Resource.Success(localArticles))
        }
    }
}
