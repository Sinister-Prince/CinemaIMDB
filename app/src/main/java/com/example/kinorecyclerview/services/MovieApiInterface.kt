package com.example.kinorecyclerview.services

import com.example.kinorecyclerview.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface{

    @GET("/3/movie/popular")
    fun getMovielist(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>


}