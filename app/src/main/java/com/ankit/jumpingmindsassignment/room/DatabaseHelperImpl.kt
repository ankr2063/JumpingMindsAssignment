package com.ankit.jumpingmindsassignment.room

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getBeers(): List<Beer> = appDatabase.BeerDao().getAll()
    override suspend fun insertAll(beers: ArrayList<Beer>) = appDatabase.BeerDao().insertAll(beers)
}