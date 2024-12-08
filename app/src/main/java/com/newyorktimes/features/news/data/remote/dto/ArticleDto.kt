package com.newyorktimes.features.news.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.newyorktimes.features.news.domain.model.Article

data class ArticleDto(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("adx_keywords")
    val adxKeywords: String,
    @SerializedName("asset_id")
    val assetId: Long,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("column")
    val column: Any,
    @SerializedName("des_facet")
    val desFacet: List<String>,
    @SerializedName("eta_id")
    val etaId: Int,
    @SerializedName("geo_facet")
    val geoFacet: List<String>,
    @SerializedName("id")
    val id: Long,
    @SerializedName("media")
    val media: List<MediaItemDto>,
    @SerializedName("nytdsection")
    val nytdsection: String,
    @SerializedName("org_facet")
    val orgFacet: List<String>,
    @SerializedName("per_facet")
    val perFacet: List<String>,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection")
    val subsection: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("url")
    val url: String
)


fun ArticleDto.toArticle() : Article {

    return Article(
        summary = `abstract`,
        keywords = adxKeywords,
        byline = byline,
        id = id,
        publishedDate = publishedDate,
        section = section,
        subsection = subsection,
        title = title,
        type = type,
        imageUrl = media.firstOrNull()?.mediaMetadata?.getOrNull(2)?.url ?: ""
    )

}