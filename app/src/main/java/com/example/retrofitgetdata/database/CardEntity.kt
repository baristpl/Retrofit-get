package com.example.retrofitgetdata.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_entity")
data class CardEntity(
    @PrimaryKey
    val thumbnail: String,
    val title: String,
    val subTitle: String,
    val type: String
)
