package com.example.retrofitgetdata.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cardEntity: CardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDepartment(department: DepartmentEntity)

    @Query("SELECT * FROM card_entity WHERE type = 'university'")
    fun getUniversityCards(): LiveData<List<CardEntity>>

    @Query("SELECT * FROM card_entity WHERE type = 'blog'")
    fun getBlogCards(): LiveData<List<CardEntity>>

    @Query("SELECT * FROM card_entity WHERE type = 'country'")
    fun getCountryCards(): LiveData<List<CardEntity>>

    @Query("SELECT * FROM department_entity")
    fun getDepartments(): LiveData<List<DepartmentEntity>>
}