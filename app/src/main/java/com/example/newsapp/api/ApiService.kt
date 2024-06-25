package com.example.newsapp.api

import com.example.newsapp.model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines") // endpoint
    fun getArticles(
        @Query("country") country: String = "us", // query
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = "48589685347e4bb5814265eb8491c32b",
    ): Call<ResponseNews>
}