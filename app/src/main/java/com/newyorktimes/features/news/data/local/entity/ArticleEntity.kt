package com.newyorktimes.features.news.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.newyorktimes.features.news.domain.model.Article


@Entity
data class ArticleEntity(
    val summary: String,
    val keywords: String,
    val byline: String,
    @PrimaryKey val id: Long,
    val imageUrl: String,
    val publishedDate: String,
    val section: String,
    val subsection: String,
    val title: String,
    val type: String
)


fun ArticleEntity.toArticle() : Article {

    return Article(
        summary = summary,
        keywords = keywords,
        byline = byline,
        id = id,
        publishedDate = publishedDate,
        section = section,
        subsection = subsection,
        title = title,
        type = type,
        imageUrl = imageUrl
    )
}