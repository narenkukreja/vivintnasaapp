package com.example.vivintnasaapp.model.interfaces

import com.example.vivintnasaapp.model.data.nasaimages.NasaImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search")
    suspend fun getImages(
        @Query("q") q : String,
        @Query("description") description : String,
        @Query("media_type") media_type : String = "image"
    ): Response<NasaImagesResponse>
}