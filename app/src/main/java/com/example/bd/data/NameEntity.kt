package com.example.bd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nameTable")
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val name:String

)