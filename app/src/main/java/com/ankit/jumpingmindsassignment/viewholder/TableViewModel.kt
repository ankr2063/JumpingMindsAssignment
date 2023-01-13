package com.ankit.jumpingmindsassignment.viewholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.jumpingmindsassignment.room.DatabaseHelper
import kotlinx.coroutines.launch

class TableViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

    private fun fetchCourses() {
        viewModelScope.launch {
            try {
                val beers = dbHelper.getBeers()
            } catch (e: Exception) {
                // handler error
            }
        }
    }
}