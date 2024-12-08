package com.newyorktimes.features.news.presentation.home

import com.newyorktimes.features.news.domain.model.Article

data class HomeState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)