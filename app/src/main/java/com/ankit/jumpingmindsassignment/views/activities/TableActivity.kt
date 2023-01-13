package com.ankit.jumpingmindsassignment.views.activities

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.ActivityTableBinding
import com.ankit.jumpingmindsassignment.room.*
import com.ankit.jumpingmindsassignment.viewholder.MainViewModel
import com.ankit.jumpingmindsassignment.viewholder.TableViewModel
import java.lang.String


class TableActivity: AppCompatActivity() {

    lateinit var dataBinding: ActivityTableBinding
    var beers =  ArrayList<Beer>()
    lateinit var tableViewModel: TableViewModel
    lateinit var dbHelper: DatabaseHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_table)

        dataBinding.imgBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(this))

        tableViewModel = ViewModelProvider(this)[TableViewModel::class.java]

        tableViewModel.observeBeersLiveData().observe(this){ response ->
            beers.addAll(response)
            setTableHeader()
            setTable()
        }

        tableViewModel.fetchBeers(dbHelper)
    }

    private fun setTableHeader(){
        val row = TableRow(this)

        for (i in 0..3){
            val tv = TextView(this)
            when(i){
                0-> tv.text = "ID"
                1-> tv.text = "NAME"
                2-> tv.text = "ABV"
                3-> tv.text = "FIRST_BREWED"
            }
            tv.setTextColor(Color.BLACK)
            tv.setTypeface(tv.typeface, Typeface.BOLD_ITALIC)
            tv.setBackgroundResource(R.drawable.row_border)
            if(i == 1){
                tv.gravity = Gravity.START
            } else {
                tv.gravity = Gravity.CENTER
            }
            tv.setPadding(10,5,10,5)
            row.addView(tv)
        }

        dataBinding.mTable.addView(row)
    }

    private fun setTable(){
        for (beer in beers){
            val row = TableRow(this)

            for (i in 0..3){
                val tv = TextView(this)
                when(i){
                    0-> tv.text = beer.id.toString()
                    1-> tv.text = beer.name
                    2-> tv.text = beer.abv.toString()
                    3-> tv.text = beer.first_brewed
                }
                tv.setTextColor(Color.BLACK)
                tv.setBackgroundResource(R.drawable.row_border)
                if(i == 1){
                    tv.gravity = Gravity.START
                } else {
                    tv.gravity = Gravity.CENTER
                }
                tv.setPadding(10,5,10,5)
                row.addView(tv)
            }

            dataBinding.mTable.addView(row)
        }
    }
}