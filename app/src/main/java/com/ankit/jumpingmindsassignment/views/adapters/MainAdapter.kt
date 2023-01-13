package com.ankit.jumpingmindsassignment.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.ListItemBeerBinding
import com.ankit.jumpingmindsassignment.models.ModelBeer

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
        /*holder.binding.title.text = test.title
        holder.binding.subject.text = test.subject

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val formatter = SimpleDateFormat("hh:mm aa, dd MMM yyyy")
        val output: String = formatter.format(parser.parse(test.schedule))

        holder.binding.date.text = output

        holder.itemView.setOnClickListener {
            holder.itemView.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(test.class_link)))
        }*/
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}