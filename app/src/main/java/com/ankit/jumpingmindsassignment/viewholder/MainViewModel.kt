package com.ankit.jumpingmindsassignment.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.jumpingmindsassignment.models.ModelBeer
import com.ankit.jumpingmindsassignment.network.RetrofitRestRepository
import com.ankit.jumpingmindsassignment.room.Beer
import com.ankit.jumpingmindsassignment.room.DatabaseHelper
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private var beersListResponse = MutableLiveData<ArrayList<ModelBeer>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    lateinit var dbHelper: DatabaseHelper

    fun getAllBeers() {
        loading.value = true
        error.value = false
        RetrofitRestRepository.api.getBeers().enqueue(object :
            Callback<ArrayList<ModelBeer>> {
            override fun onResponse(call: Call<ArrayList<ModelBeer>>, response: Response<ArrayList<ModelBeer>>) {
                beersListResponse.value = response.body()

                val beers =  ArrayList<Beer>()

                for (beer in beersListResponse.value!!){
                    beers.add(Beer(beer.id, beer.name, beer.first_brewed, beer.abv))
                }

                viewModelScope.launch {
                    dbHelper.insertAll(beers)
                }

                loading.value = false
            }

            override fun onFailure(call: Call<ArrayList<ModelBeer>>, t: Throwable) {
                loading.value = false
                error.value = true

            }
        })
    }

    fun observeBeersLiveData(): LiveData<ArrayList<ModelBeer>> {
        return beersListResponse
    }
}