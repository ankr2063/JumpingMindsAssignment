package com.ankit.jumpingmindsassignment.room

import androidx.room.*

@Dao
interface BeerDao {
    @Query("SELECT * FROM Beer")
    suspend fun getAll(): List<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: ArrayList<Beer>)

    @Delete
    suspend fun delete(beer: Beer)
}