package com.newyorktimes.features.news.domain.repository

import com.newyorktimes.features.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getRemoteArticles() : List<Article>

    fun getLocalArticles() : Flow<List<Article>>

    suspend fun saveArticles(articles: List<Article>)
}