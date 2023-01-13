package com.ankit.jumpingmindsassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Beer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BeerDao(): BeerDao
}