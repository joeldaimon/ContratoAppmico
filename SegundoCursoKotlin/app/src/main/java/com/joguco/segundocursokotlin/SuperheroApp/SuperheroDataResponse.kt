package com.joguco.segundocursokotlin.SuperheroApp

import com.google.gson.annotations.SerializedName

data class SuperheroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<Superhero>
)

data class Superhero(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("image") val image: SuperheroImage
)

data class SuperheroImage(@SerializedName("url") val url: String)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String,
    )