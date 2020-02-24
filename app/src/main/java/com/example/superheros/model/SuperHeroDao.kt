package com.example.superheros.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperHeroDao {
    @Query("SELECT * FROM superheros")
    fun getSuperHeros():List<SuperHero>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuperHero(superheros: SuperHero)
}