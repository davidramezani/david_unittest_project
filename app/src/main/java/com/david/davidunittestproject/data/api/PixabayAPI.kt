package com.david.davidunittestproject.data.api

import com.david.davidunittestproject.BuildConfig
import com.david.davidunittestproject.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ) : Response<ImageResponse>
}