package com.example.retrofitgetdata.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Database(
    entities = [CardEntity::class, DepartmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CardDatabase : RoomDatabase() {

    abstract val databaseDao: CardDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardDatabase::class.java,
                        "home_page_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}