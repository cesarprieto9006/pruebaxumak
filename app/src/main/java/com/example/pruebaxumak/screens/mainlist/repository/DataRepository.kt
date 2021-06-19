package com.example.pruebaxumak.screens.mainlist.repository

import androidx.lifecycle.LiveData
import com.example.pruebaxumak.database.DataDao
import com.example.pruebaxumak.remote.DataApi
import com.example.pruebaxumak.screens.mainlist.model.DataResponse
import com.example.pruebaxumak.utils.SuperAppConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val movieApi: DataApi,
    private val localDataSource: DataDao
) {

    suspend fun getList() =
        movieApi.getAllList(SuperAppConstants.LIMIT)

    suspend fun saveFavorite(data:DataResponse){
        localDataSource.insert(data)
    }

    fun deleteFavorite(id:Int){
        localDataSource.delete(id)
    }

    fun getAllFavorites(): LiveData<List<DataResponse>> =
        localDataSource.getAllData()

}