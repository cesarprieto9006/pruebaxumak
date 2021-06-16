package com.example.pruebaxumak.repository

import com.example.pruebaxumak.local.DataDao
import com.example.pruebaxumak.remote.DataRemoteDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteDataSource: DataRemoteDataSource,
    private val localDataSource: DataDao
) {

}