package com.example.pruebaxumak.screens.mainlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data")
data class DataResponse(

    @SerializedName("char_id")
    var char_id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("birthday")
    var birthday: String,
    @SerializedName("img")
    var img: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("nickname")
    var nickname: String,
    @SerializedName("portrayed")
    var portrayed: String,
    @SerializedName("category")
    var category: String,
    @PrimaryKey
    val id: Int,
    val State:Boolean=false

)