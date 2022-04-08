package com.example.ventazapas.ui.fragment.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.databinding.FragmentShoesBinding
import com.example.ventazapas.ui.adapter.ShoesAdapter
import com.example.ventazapas.ui.viewModel.ShoesViewModel

class ShoesFragment : Fragment() {

    private val shoesViewModel by viewModels<ShoesViewModel>()

    lateinit var binding: FragmentShoesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentShoesBinding.inflate(inflater,container, false)


        return binding.root
    }

    fun initRecyclerView(list : List<MockShoes>){
        val adapter = ShoesAdapter(list)
        binding
    }
}