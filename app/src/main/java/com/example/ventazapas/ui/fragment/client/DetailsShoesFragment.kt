package com.example.ventazapas.ui.fragment.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.ventazapas.data.fireStore.FireStoreImp
import com.example.ventazapas.databinding.FragmentDetailsShoesBinding
import com.squareup.picasso.Picasso

class DetailsShoesFragment : Fragment() {

    private val prueba1 = FireStoreImp()
    private lateinit var binding: FragmentDetailsShoesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsShoesBinding.inflate(inflater, container, false)

        prueba1.getShoesById(getBundle()).observe(viewLifecycleOwner) {
            if (it.state_offer) {
                Picasso.get().load(it.image[0]).into(binding.ivMoreSeen)
                binding.tvGender.text = it.gender
                binding.tvName.text = it.name
                binding.tvOldPrice.text = "$ ${it.price}"
                binding.tvOldPrice.isVisible = true
                binding.tvPrice.text = "$${it.offer_price}"
                binding.tvMoney.text = "${it.discount_rate}%"
            } else {
                Picasso.get().load(it.image[0]).into(binding.ivMoreSeen)
                binding.tvGender.text = it.gender
                binding.tvName.text = it.name
                binding.tvPrice.text = "$ ${it.price}"
                binding.tvOldPrice.isVisible = false
                binding.tvMoney.isVisible = false
            }
        }

        return binding.root
    }

    private fun getBundle(): String {
        val date = arguments?.getString("id").toString()
        return date
    }
}