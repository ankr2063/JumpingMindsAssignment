package com.ankit.jumpingmindsassignment.views.activities

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.ActivityTableBinding
import com.ankit.jumpingmindsassignment.room.Beer
import java.lang.String


class TableActivity: AppCompatActivity() {

    lateinit var dataBinding: ActivityTableBinding
    var beers =  ArrayList<Beer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_table)

        setTableHeader()

    }

    private fun setTableHeader(){
        val row = TableRow(this)

        val tv = TextView(this)

        tv.text = "ID"
        tv.setTextColor(Color.BLACK)
        tv.setTypeface(tv.typeface, Typeface.BOLD_ITALIC)
        row.addView(tv)

        tv.text = "NAME"
        row.addView(tv)

        tv.text = "ABV"
        row.addView(tv)

        tv.text = "FIRST_BREWED"
        row.addView(tv)

        dataBinding.mTableHeader.addView(row)
    }

    private fun setTable(){
        for (beer in beers){
            val row = TableRow(this)
            val tv = TextView(this)

            tv.text = beer.id.toString()
            tv.setTextColor(Color.BLACK)
            tv.setTypeface(tv.typeface, Typeface.BOLD_ITALIC)
            row.addView(tv)

            tv.text = beer.name
            row.addView(tv)

            tv.text = beer.abv.toString()
            row.addView(tv)

            tv.text = beer.first_brewed
            row.addView(tv)

            dataBinding.mTable.addView(row)
        }
    }
}