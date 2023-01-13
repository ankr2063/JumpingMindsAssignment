package com.ankit.jumpingmindsassignment.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BeerDao {
    @Query("SELECT * FROM Beer")
    suspend fun getAll(): List<Beer>

    @Insert
    suspend fun insertAll(Courses: List<Beer>)

    @Delete
    suspend fun delete(beer: Beer)
}