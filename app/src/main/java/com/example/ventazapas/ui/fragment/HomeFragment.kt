package com.example.ventazapas.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ventazapas.R
import com.example.ventazapas.data.fireStore.FireStoreImp
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.data.model.ResponseShoes
import com.example.ventazapas.databinding.FragmentHomeBinding
import com.example.ventazapas.ui.adapter.ShoesAdapter
import com.example.ventazapas.ui.fragment.client.DetailsShoesFragment
import com.example.ventazapas.ui.viewModel.HomeViewModel
import com.example.ventazapas.ui.viewModel.ShoesViewModel

class HomeFragment : Fragment() {

    val prueba = FireStoreImp()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)


        prueba.getAllShoes().observe(viewLifecycleOwner,{

            initRecyclerViewRecommended(it)
        })
        prueba.getListShoesByOffert().observe(viewLifecycleOwner,{
            initRecyclerView(it)
        })

        return binding.root
    }
    private fun initRecyclerView(list: List<ResponseShoes>) {
        val adapter = ShoesAdapter(list)
        binding.rvMoreSeen.adapter = adapter
        adapter.setOnClickListener(object : ShoesAdapter.OnClickListener {
            override fun onItemClick(position: Int) {

                val bundle = Bundle()
                bundle.putString("id", list[position].id.toString())
                val fragment = DetailsShoesFragment()
                fragment.arguments = bundle
                findNavController().navigate(R.id.detailsShoesFragment, bundle)
            }
        })
    }
    private fun initRecyclerViewRecommended(list: List<ResponseShoes>) {
        val adapter = ShoesAdapter(list)
        binding.rvRecommended.adapter = adapter
        adapter.setOnClickListener(object : ShoesAdapter.OnClickListener {
            override fun onItemClick(position: Int) {

                val bundle = Bundle()
                bundle.putString("id", list[position].id.toString())
                val fragment = DetailsShoesFragment()
                fragment.arguments = bundle
                findNavController().navigate(R.id.detailsShoesFragment, bundle)
            }
        })
    }
}