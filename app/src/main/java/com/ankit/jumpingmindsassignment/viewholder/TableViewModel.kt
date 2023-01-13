package com.ankit.jumpingmindsassignment.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.jumpingmindsassignment.models.ModelBeer
import com.ankit.jumpingmindsassignment.room.Beer
import com.ankit.jumpingmindsassignment.room.DatabaseHelper
import com.ankit.jumpingmindsassignment.room.DatabaseHelperImpl
import kotlinx.coroutines.launch

class TableViewModel : ViewModel() {

    private var beers = MutableLiveData<List<Beer>>()

    fun fetchBeers(dbHelper: DatabaseHelperImpl) {
        viewModelScope.launch {
            println("asdf sizee"+dbHelper.getBeers())
            beers.value = dbHelper.getBeers()
        }
    }

    fun observeBeersLiveData(): LiveData<List<Beer>> {
        return beers
    }
}