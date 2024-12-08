package com.newyorktimes.features.news.data.repository

import com.newyorktimes.features.news.data.local.ArticleDatabase
import com.newyorktimes.features.news.data.local.entity.toArticle
import com.newyorktimes.features.news.data.remote.NewsApi
import com.newyorktimes.features.news.data.remote.dto.toArticle
import com.newyorktimes.features.news.domain.model.Article
import com.newyorktimes.features.news.domain.model.toArticleEntity
import com.newyorktimes.features.news.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDatabase: ArticleDatabase
) : ArticleRepository {

    override suspend fun getRemoteArticles(): List<Article> {
        return  newsApi.getPopularNews().results.map { it.toArticle() }
    }

    override  fun getLocalArticles(): Flow<List<Article>> {
        return articleDatabase.articleDao.getArticles().map { articleEntityList -> articleEntityList.map { it.toArticle() } }
    }

    override suspend fun saveArticles(articles: List<Article>) {
        articleDatabase.articleDao.insertArticles(articles.map { it.toArticleEntity() })
    }

}