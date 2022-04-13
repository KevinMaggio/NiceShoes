package com.example.ventazapas.ui.fragment.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.ventazapas.data.fireStore.FireStoreImp
import com.example.ventazapas.databinding.FragmentDetailsShoesBinding
import com.example.ventazapas.ui.adapter.ShoesDetailsAdapter
import com.squareup.picasso.Picasso
import android.R
import android.graphics.Paint

import android.widget.TextView
import androidx.navigation.fragment.findNavController


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
                initRecyclerView(it.image)
                binding.tvGender.text = it.gender
                binding.tvName.text = it.name
                binding.tvOldPrice.text = "$ ${it.price}"
                binding.tvOldPrice.isVisible = true
                binding.tvPrice.text = "$${it.offer_price}"
                binding.tvMoney.text = "${it.discount_rate}%"
                binding.tvDetails.text = it.description
                binding.tvColor.text = it.color
                binding.tvGender.text = it.gender
                binding.tvType.text = it.group

                binding.tvOldPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                initRecyclerView(it.image)
                binding.tvName.text = it.name
                binding.tvPrice.text = "$ ${it.price}"
                binding.tvOldPrice.isVisible = false
                binding.tvMoney.isVisible = false
                binding.tvNameOldPrice.isVisible = false
                binding.tvDetails.text = it.description
                binding.tvColor.text = it.color
                binding.tvGender.text = it.gender
                binding.tvType.text = it.group



            }

            binding.btCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }



        return binding.root
    }

    private fun getBundle(): String {
        val date = arguments?.getString("id").toString()
        return date
    }

    private fun initRecyclerView(list: List<String>) {
        val adapter = ShoesDetailsAdapter(list)
        binding.rvImages.adapter = adapter
    }
}