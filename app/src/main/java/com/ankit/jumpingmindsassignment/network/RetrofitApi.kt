package com.ankit.jumpingmindsassignment.network

import com.ankit.jumpingmindsassignment.models.ModelBeer
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("beers")
    fun getBeers(): Call<ArrayList<ModelBeer>>
}