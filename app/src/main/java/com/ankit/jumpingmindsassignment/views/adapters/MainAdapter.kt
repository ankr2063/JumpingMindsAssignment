package com.ankit.jumpingmindsassignment.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.ListItemBeerBinding
import com.ankit.jumpingmindsassignment.models.ModelBeer
import com.bumptech.glide.Glide

class MainAdapter (val beers: ArrayList<ModelBeer>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(var binding: ListItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemBeerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_beer,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beers[position]
        holder.binding.title.text = beer.name
        holder.binding.mTvAbv.text = "ABV: "+beer.abv

        Glide.with(holder.itemView).load(beer.image_url).centerCrop()
            .into(holder.binding.beerThumbnail)

        holder.binding.imgInfo.setOnClickListener {
            showDialog(it.context, beer)
        }

        holder.itemView.setOnClickListener {
            showDialog(it.context, beer)
        }
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    private fun showDialog(context: Context, beer: ModelBeer){

    }
}