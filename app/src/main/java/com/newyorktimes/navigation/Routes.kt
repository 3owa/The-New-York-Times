package com.newyorktimes.navigation

import com.newyorktimes.features.news.domain.model.Article
import kotlinx.serialization.Serializable


@Serializable
sealed class Routes {

    @Serializable
    object Home

    @Serializable
    data class Detail(val article: Article)
}