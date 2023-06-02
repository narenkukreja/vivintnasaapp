package com.example.vivintnasaapp.model.repositories

import com.example.vivintnasaapp.model.data.nasaimages.NasaImagesResponse
import com.example.vivintnasaapp.model.interfaces.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NasaRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://images-api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun getImages(q: String, description: String): NasaImagesResponse? {
        return try {
            withContext(Dispatchers.IO) {
                val response = retrofit.getImages(q, description)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}