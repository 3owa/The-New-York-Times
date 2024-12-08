package com.newyorktimes.features.news.data.remote

import com.newyorktimes.core.contants.ApiConstants
import com.newyorktimes.features.news.data.remote.dto.ArticlesResponseDto
import retrofit2.http.GET

interface NewsApi {

    @GET("v2/viewed/1.json?api-key=${ApiConstants.API_KEY}")
    suspend fun getPopularNews() : ArticlesResponseDto

}