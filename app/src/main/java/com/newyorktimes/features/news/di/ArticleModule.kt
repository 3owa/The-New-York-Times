package com.newyorktimes.features.news.di

import android.app.Application
import androidx.room.Room
import com.newyorktimes.core.contants.ApiConstants
import com.newyorktimes.core.contants.DbConstants
import com.newyorktimes.features.news.data.local.ArticleDatabase
import com.newyorktimes.features.news.data.remote.NewsApi
import com.newyorktimes.features.news.data.repository.ArticleRepositoryImpl
import com.newyorktimes.features.news.domain.repository.ArticleRepository
import com.newyorktimes.features.news.domain.usecase.GetArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ArticleModule {

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {

        val client = OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .callTimeout(2, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideArticleDatabase(application: Application) : ArticleDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ArticleDatabase::class.java,
            name = DbConstants.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideArticleRepository(newsApi: NewsApi, articleDatabase: ArticleDatabase) : ArticleRepository {
        return ArticleRepositoryImpl(newsApi,articleDatabase)
    }

    @Provides
    @Singleton
    fun provideGetArticlesUseCase(articleRepository: ArticleRepository) : GetArticlesUseCase {
        return GetArticlesUseCase(articleRepository)
    }

}