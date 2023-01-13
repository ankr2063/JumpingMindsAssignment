package com.ankit.jumpingmindsassignment.views.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.Html
import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankit.jumpingmindsassignment.R
import com.ankit.jumpingmindsassignment.databinding.DialogDetailsBinding
import com.ankit.jumpingmindsassignment.databinding.ListItemBeerBinding
import com.ankit.jumpingmindsassignment.models.ModelBeer
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat


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

        Glide.with(holder.itemView).load(beer.image_url)
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
        val dialog = Dialog(context)
        val binding: DialogDetailsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_details, null, false)
        dialog.setContentView(binding.root)
        dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(0x7000000))
        dialog.setCancelable(false)

        binding.title.text = beer.name
        binding.mTvAbv.text = "ABV: "+beer.abv
        val parser = SimpleDateFormat("MM/yyyy")
        val formatter = SimpleDateFormat("MMM yyyy")
        val output: String = formatter.format(parser.parse(beer.first_brewed))
        binding.mTvFirstBrewed.text = "Brewed since $output"
        binding.mTvTagLine.text = beer.tagline
        binding.mTvDescription.text = beer.description
        val tips = "<b>Tips:</b> "+beer.brewers_tips
        binding.mTvTips.text = Html.fromHtml(tips, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.mTvFoodPairing.text = beer.food_pairing.toBulletedList()

        Glide.with(context).load(beer.image_url)
            .into(binding.beerThumbnail)

        dialog.show()

        binding.close.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun List<String>.toBulletedList(): CharSequence {
        return SpannableString(this.joinToString("\n")).apply {
            this@toBulletedList.foldIndexed(0) { index, acc, span ->
                val end = acc + span.length + if (index != this@toBulletedList.size - 1) 1 else 0
                this.setSpan(BulletSpan(16), acc, end, 0)
                end
            }
        }
    }
}