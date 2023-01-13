package com.ankit.jumpingmindsassignment.room

interface DatabaseHelper {

    suspend fun getBeers(): List<Beer>

    suspend fun insertAll(beers: List<Beer>)
}