package com.example.ventazapas.ui.fragment.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ventazapas.databinding.FragmentOrdersBinding

class MyOrdersFragment : Fragment() {

    lateinit var binding: FragmentOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOrdersBinding.inflate(inflater,container,false)

        return binding.root
    }
}