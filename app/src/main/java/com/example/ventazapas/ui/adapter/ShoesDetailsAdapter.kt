package com.example.ventazapas.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ventazapas.R
import com.example.ventazapas.databinding.ItemDetailsBinding
import com.squareup.picasso.Picasso

class ShoesDetailsAdapter (private val list : List<String>): RecyclerView.Adapter <ShoesDetailsAdapter.ShoesDetailsHolder> () {
class ShoesDetailsHolder(val binding: ItemDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesDetailsHolder {
        val binding = ItemDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShoesDetailsHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoesDetailsHolder, position: Int) {
        val shoes = list[position]
        //Picasso.get().load(shoes).into(holder.binding.ivDetails)
        Glide.with(holder.binding.ivDetails).load(shoes).into(holder.binding.ivDetails)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}