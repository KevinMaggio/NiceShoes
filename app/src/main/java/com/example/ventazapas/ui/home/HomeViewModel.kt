package com.example.ventazapas.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.data.repository.ShoesRepository

class HomeViewModel : ViewModel() {

    val shoesRepository = ShoesRepository()
    val listMockShoes = MutableLiveData<List<MockShoes>>()

    fun getMockShoes() {
        listMockShoes.value = shoesRepository.getMockShoes()
    }

}
