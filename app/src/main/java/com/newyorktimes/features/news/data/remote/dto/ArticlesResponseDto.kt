package com.newyorktimes.features.news.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ArticlesResponseDto(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<ArticleDto>,
    @SerializedName("status")
    val status: String
)