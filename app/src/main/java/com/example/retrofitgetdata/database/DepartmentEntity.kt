package com.example.retrofitgetdata.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department_entity")
data class DepartmentEntity(
    @PrimaryKey
    val name: String
)
