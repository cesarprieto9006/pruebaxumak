package com.example.pruebaxumak.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebaxumak.screens.mainlist.model.DataResponse

@Dao
interface DataDao {
    @Query("SELECT * FROM data ORDER BY state DESC")
    fun getAllDataOrder() : LiveData<List<DataResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<DataResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DataResponse)

}