package com.example.citiessdk.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.citiessdk.cities.data.Cities
import com.example.citiessdk.cities.data.City
import com.example.citiessdk.cities.data.CityDao

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun citiesDao(): CityDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext, AppDatabase::class.java, "city"
                        ).build()
                    }
                }
            }
            return instance!!
        }
    }
}