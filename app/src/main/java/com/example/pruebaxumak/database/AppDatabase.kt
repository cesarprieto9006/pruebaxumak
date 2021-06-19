package com.example.pruebaxumak.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebaxumak.screens.mainlist.model.DataResponse


@Database(entities = [DataResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val LOCK = Any()
        private var DB_NAME = "civica_database"

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(LOCK) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}