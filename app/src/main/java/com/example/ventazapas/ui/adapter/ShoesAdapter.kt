package com.example.ventazapas.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ventazapas.R
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.databinding.ItemShoesBinding
import com.squareup.picasso.Picasso

class ShoesAdapter (private val list : List<MockShoes>): RecyclerView.Adapter<ShoesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shoes,parent,false)
        return ShoesHolder(view)
    }

    override fun onBindViewHolder(holder: ShoesHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class ShoesHolder(view: View): RecyclerView.ViewHolder(view){
    private val binding = ItemShoesBinding.bind(view)

    fun render(shoes: MockShoes){
        Picasso.get().load(shoes.image[0]).into(binding.ivMoreSeen)
        binding.tvGender.text= shoes.gender.name
        binding.tvName.text= shoes.name
        binding.tvPrice.text= shoes.price.toString()
    }
}