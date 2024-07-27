package com.joguco.segundocursokotlin.SuperheroApp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/2604689389699609/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName:String):Response<SuperheroDataResponse>

    @GET("/api/2604689389699609/{id}")
    suspend fun getSuperheroedetail(@Path("id") superheroId:String):Response<SuperheroDetailResponse>
}