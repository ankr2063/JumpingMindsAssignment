package com.ankit.jumpingmindsassignment.views.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.ActivityMainBinding
import com.ankit.jumpingmindsassignment.models.ModelBeer
import com.ankit.jumpingmindsassignment.room.*
import com.ankit.jumpingmindsassignment.viewholder.MainViewModel
import com.ankit.jumpingmindsassignment.views.adapters.MainAdapter

class MainActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: MainAdapter
    private var beers = ArrayList<ModelBeer>()
    lateinit var dialog: Dialog
    lateinit var dbHelper: DatabaseHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(this))

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        dataBinding.beersRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mainAdapter = MainAdapter(beers)
        dataBinding.beersRV.adapter = mainAdapter
        dataBinding.beersRV.hasFixedSize()

        initialiseDialog()

        mainViewModel.observeBeersLiveData().observe(this) { response ->
            beers.clear()
            beers.addAll(response)
            mainAdapter.notifyDataSetChanged()
            dataBinding.swipeRefreshLayout.isRefreshing = false
        }

        mainViewModel.loading.observe(this) {
            if (it) {
                showLoadingDialog()
            } else {
                hideLoadingDialog()
            }
        }

        mainViewModel.error.observe(this) {
            if (it) {
                dataBinding.errorPlaceholder.visibility = View.VISIBLE
            } else {
                dataBinding.errorPlaceholder.visibility = View.INVISIBLE
            }
            dataBinding.swipeRefreshLayout.isRefreshing = false
        }

        mainViewModel.getAllBeers(dbHelper)

        dataBinding.mTvTable.setOnClickListener {
            startActivity(Intent(this, TableActivity::class.java))
        }

        dataBinding.swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.getAllBeers(dbHelper)
        }
    }

    private fun showLoadingDialog() {
        if (dialog == null) {
            initialiseDialog()
        }
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    private fun hideLoadingDialog() {
        if (dialog == null) {
            initialiseDialog()
        }
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    private fun initialiseDialog() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_loading)
        dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(0x7000000))
        dialog.setCancelable(false)
    }
}