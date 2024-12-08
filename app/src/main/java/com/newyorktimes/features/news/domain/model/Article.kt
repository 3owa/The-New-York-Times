package com.newyorktimes.features.news.domain.model

import android.os.Parcelable
import com.newyorktimes.features.news.data.local.entity.ArticleEntity
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable



@Serializable
@Parcelize
data class Article(
    val summary: String,
    val keywords: String,
    val byline: String,
    val id: Long,
    val imageUrl: String,
    val publishedDate: String,
    val section: String,
    val subsection: String,
    val title: String,
    val type: String
):Parcelable


fun Article.toArticleEntity() : ArticleEntity {

    return ArticleEntity(
        summary = summary,
        keywords = keywords,
        byline = byline,
        id = id,
        imageUrl = imageUrl,
        publishedDate = publishedDate,
        section = section,
        subsection = subsection,
        title = title,
        type = type
    )

}