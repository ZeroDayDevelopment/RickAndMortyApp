package com.example.ricknmorty.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page:Int)
}