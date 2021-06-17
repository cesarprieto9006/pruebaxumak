package com.example.pruebaxumak.remote

import com.example.pruebaxumak.screens.mainlist.model.MainResponse
import retrofit2.http.GET


interface DataApi {

    @GET("api/characters?limit=50")
    suspend fun getAllList(): List<MainResponse>


}