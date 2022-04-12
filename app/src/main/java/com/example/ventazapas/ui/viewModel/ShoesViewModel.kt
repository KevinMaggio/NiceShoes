package com.example.ventazapas.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.data.repository.ShoesRepository

class ShoesViewModel : ViewModel() {

    val shoesRepository = ShoesRepository()
    val listMockShoes = MutableLiveData <List<MockShoes>>()

    fun getMockShoe(){
        listMockShoes.value= shoesRepository.getMockShoes()
    }

}