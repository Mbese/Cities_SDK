package com.example.superheros.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SuperHero::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun superherosDoa(): SuperHeroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext, AppDatabase::class.java, "superheros"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}