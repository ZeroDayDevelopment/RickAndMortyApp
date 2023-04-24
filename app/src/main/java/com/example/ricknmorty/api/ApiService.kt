package com.example.ricknmorty.api


import com.example.ricknmorty.episoderesponse.EpisodeResponse
import com.example.ricknmorty.locationresponse.LocationResponse
import com.example.ricknmorty.response.charresponse.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Response

interface ApiService {
    @GET("character")
     suspend fun getAllCharacters(@Query("page") page:Int):Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id")id:Int):Response<com.example.ricknmorty.response.charresponse.Result>

   @GET("location")
    suspend fun getAllLocations(@Query("page") page:Int):Response<LocationResponse>

    @GET("location/{id}")
    suspend fun getSingleLocation(@Path("id")id:Int):Response<com.example.ricknmorty.locationresponse.Result>

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page:Int):Response<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getSingleEpisode(@Path("id")id:Int):Response<com.example.ricknmorty.episoderesponse.Result>
}