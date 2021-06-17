package com.example.pruebaxumak.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebaxumak.screens.mainlist.model.MainResponse

@Dao
interface DataDao {
    @Query("SELECT * FROM data")
    fun getAllCharacters() : LiveData<List<MainResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<MainResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<MainResponse>)


}