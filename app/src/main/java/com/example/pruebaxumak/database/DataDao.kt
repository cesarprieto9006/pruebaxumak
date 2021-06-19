package com.example.pruebaxumak.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebaxumak.screens.mainlist.model.DataResponse

@Dao
interface DataDao {
    @Query("SELECT * FROM data")
    fun getAllData() : LiveData<List<DataResponse>>

    @Query("DELETE FROM data WHERE char_id=:id")
    fun delete(id: Int) : LiveData<List<DataResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<DataResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DataResponse)

}