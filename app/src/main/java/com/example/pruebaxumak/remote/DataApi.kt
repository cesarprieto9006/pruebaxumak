package com.example.pruebaxumak.remote

import com.example.pruebaxumak.screens.mainlist.model.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface DataApi {

    @GET("api/characters")
    suspend fun getAllList(@Query("limit") limit: Int): List<DataResponse>


}