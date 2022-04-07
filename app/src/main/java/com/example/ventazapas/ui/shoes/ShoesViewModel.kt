package com.example.ventazapas.ui.shoes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.data.repository.ShoesRepository

class ShoesViewModel : ViewModel() {

    val shoesRepository = ShoesRepository()
    val listMockShoes = MutableLiveData <List<MockShoes>>()

    fun getMockShoes(){
        listMockShoes.value= shoesRepository.getMockShoes()
    }

}