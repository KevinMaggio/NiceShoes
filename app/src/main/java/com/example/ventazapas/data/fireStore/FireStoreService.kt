package com.example.ventazapas.data.fireStore

import androidx.lifecycle.MutableLiveData
import com.example.ventazapas.data.model.ResponseShoes
import com.example.ventazapas.data.model.ResponseUser

interface FireStoreService {

    fun addUser(
        title: String,
        debt: Int,
        direction: String,
        dni: String,
        email: String,
        favorite: List<Int>,
        idEdit: Int,
        name: String,
        number: String,
        orders: List<Int>,
        shopping: List<Int>,
        state_account: Int,
        type: String
    ): String

    fun getUser(title: String): MutableLiveData<ResponseUser>

    fun editUser(): MutableLiveData<ResponseUser>

    fun addShoes(): MutableLiveData<ResponseShoes>

    fun getShoesById(name: String,id:String,code:String): MutableLiveData<ResponseShoes>

    fun getAllShoes():MutableLiveData<List<ResponseShoes>>

    fun editShoes(): MutableLiveData<ResponseShoes>
}