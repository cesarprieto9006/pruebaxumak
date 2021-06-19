package com.example.pruebaxumak.screens.mainlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data")
data class DataResponse(

    @PrimaryKey
    @ColumnInfo(name = "char_id")
    @SerializedName("char_id")
    var char_id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,
    @ColumnInfo(name = "birthday")
    @SerializedName("birthday")
    var birthday: String,
    @ColumnInfo(name = "img")
    @SerializedName("img")
    var img: String,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    var status: String,
    @ColumnInfo(name = "nickname")
    @SerializedName("nickname")
    var nickname: String,
    @ColumnInfo(name = "portrayed")
    @SerializedName("portrayed")
    var portrayed: String,
    @ColumnInfo(name = "category")
    @SerializedName("category")
    var category: String,
    @ColumnInfo(name = "state")
    var State:Boolean=false

)