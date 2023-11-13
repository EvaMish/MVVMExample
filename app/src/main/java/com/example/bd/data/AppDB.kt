package com.example.bd.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NameEntity::class], version = 1
)
abstract class AppDB : RoomDatabase() {
    abstract val dao: Dao

    companion object {
        fun createDB(context: Context): AppDB {
            return Room.databaseBuilder(
                context,
                AppDB::class.java,
                "test.db"
            ).build()
        }

    }
}