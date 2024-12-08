package com.newyorktimes.features.news.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MediaItemDto(
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int,
    @SerializedName("caption")
    val caption: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String
)