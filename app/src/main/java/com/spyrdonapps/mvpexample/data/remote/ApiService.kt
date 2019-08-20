package com.spyrdonapps.mvpexample.data.remote

import retrofit2.http.GET

interface ApiService {

    @GET("channels.json")
    suspend fun getResponse(): ApiResponse
}