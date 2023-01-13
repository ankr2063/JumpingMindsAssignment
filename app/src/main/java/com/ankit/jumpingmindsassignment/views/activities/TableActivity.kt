package com.ankit.jumpingmindsassignment.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.ActivityTableBinding

class TableActivity: AppCompatActivity() {

    lateinit var dataBinding: ActivityTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_table)


    }
}