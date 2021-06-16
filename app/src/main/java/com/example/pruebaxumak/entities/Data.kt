package com.example.pruebaxumak.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class Data(

    @PrimaryKey
    val id: Int

)