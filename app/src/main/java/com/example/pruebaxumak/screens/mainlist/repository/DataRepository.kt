package com.example.pruebaxumak.screens.mainlist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pruebaxumak.remote.DataApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val movieApi: DataApi
) {
    fun getList() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DataPagingResource(movieApi) }
        ).liveData
}