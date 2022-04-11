package com.example.ventazapas.data.fireStore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ventazapas.data.model.ResponseShoes
import com.example.ventazapas.data.model.ResponseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class FireStoreImp : FireStoreService {

    private val fireStore = FirebaseFirestore.getInstance()
    private val liveUser = MutableLiveData<ResponseUser>()
    private val liveShoes = MutableLiveData<ResponseShoes>()
    private val liveDelete = MutableLiveData<Boolean>()
    private val liveAllShoes = MutableLiveData<List<ResponseShoes>>()

    override fun addUser(
        title: String,
        debt: Int,
        direction: String,
        dni: String,
        email: String,
        favorite: List<String>,
        idEdit: Int,
        name: String,
        number: String,
        orders: List<String>,
        shopping: List<String>,
        state_account: Int,
        type: String
    ): String {
        if (fireStore.collection("user").document(title).set(
                hashMapOf(
                    "debt" to debt,
                    "direction" to direction,
                    "dni" to dni,
                    "email" to email,
                    "favorite" to favorite,
                    "id_edit" to idEdit,
                    "name" to name,
                    "number" to number,
                    "orders" to orders,
                    "shopping" to shopping,
                    "state_account" to state_account,
                    "type" to type

                )
            ).isCanceled
        ) {
            return "success"

        } else {
            return "error"
        }
    }


    override fun getUser(title: String): MutableLiveData<ResponseUser> {
        fireStore.collection("user").document(title).get().addOnSuccessListener {
            if (!it.data.isNullOrEmpty()) {
                liveUser.postValue(
                    ResponseUser(
                        it.get("debt").toString().toInt(),
                        it.get("direction").toString(),
                        it.get("dni").toString(),
                        it.get("email").toString(),
                        it.get("favorite") as List<String>,
                        it.get("id_edit").toString().toInt(),
                        it.get("name").toString(),
                        it.get("number").toString(),
                        it.get("orders") as List<String>,
                        it.get("shopping") as List<String>,
                        it.get("state_account").toString().toInt(),
                        it.get("type").toString()
                    )
                )
            } else {
                liveUser.postValue(
                    ResponseUser(
                        0,
                        "",
                        "",
                        "",
                        listOf(),
                        0,
                        "empty",
                        "",
                        listOf(),
                        listOf(),
                        0,
                        ""
                    )
                )
            }

        }.addOnFailureListener {
            Log.d("res", "error")

        }
        return liveUser
    }


    override fun addShoes(
        code: String,
        color: String,
        description: String,
        discount_rate: String,
        gender: String,
        group: String,
        id: String,
        image: List<String>,
        name: String,
        offer_price: Int,
        price: Int,
        state_offer: Boolean,
        waist: String
    ) {
        fireStore.collection("shoes").document(id).set(
            hashMapOf(
                "code" to code,
                "color" to color,
                "description" to description,
                "discount_rate" to discount_rate,
                "gender" to gender,
                "group" to group,
                "id" to id,
                "image" to image,
                "name" to name,
                "offer_price" to offer_price,
                "price" to price,
                "state_offer" to state_offer,
                "waist" to waist
            )
        )
    }

    override fun getShoesById(id: String): MutableLiveData<ResponseShoes> {
        fireStore.collection("shoes").document(id).get()
            .addOnSuccessListener {

                if (!it.data.isNullOrEmpty()) {
                    liveShoes.postValue(
                        ResponseShoes(
                            it.get("code").toString(),
                            it.get("color").toString(),
                            it.get("description").toString(),
                            it.get("discount_rate").toString(),
                            it.get("gender").toString(),
                            it.get("group").toString(),
                            it.get("id").toString().toInt(),
                            it.get("image") as List<String>,
                            it.get("name").toString(),
                            it.get("offer_price").toString().toInt(),
                            it.get("price").toString().toInt(),
                            it.get("state_offer").toString().toBoolean(),
                            it.get("waist").toString()
                        )
                    )
                } else {
                    liveShoes.postValue(
                        ResponseShoes(
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            0,
                            listOf(),
                            "empty",
                            0,
                            0,
                            false,
                            ""
                        )
                    )
                }

            }
        return liveShoes
    }

    override fun getAllShoes(): MutableLiveData<List<ResponseShoes>> {
        fireStore.collection("shoes").get()
            .addOnSuccessListener {
                val list = mutableListOf<ResponseShoes>()
                for (i in it.documents) {
                    list.add(
                        ResponseShoes(
                            i.get("code").toString(),
                            i.get("color").toString(),
                            i.get("description").toString(),
                            i.get("discount_rate").toString(),
                            i.get("gender").toString(),
                            i.get("group").toString(),
                            i.get("id").toString().toInt(),
                            i.get("image") as List<String>,
                            i.get("name").toString(),
                            i.get("offer_price").toString().toInt(),
                            i.get("price").toString().toInt(),
                            i.get("state_offer").toString().toBoolean(),
                            i.get("waist").toString()
                        )
                    )
                }
                liveAllShoes.postValue(list)
            }
        return liveAllShoes
    }


    override fun deleteShoes(id: String): MutableLiveData<Boolean> {
        fireStore.collection("shoes").document(id)
            .delete()
            .addOnSuccessListener { liveDelete.postValue(true) }
            .addOnFailureListener { liveDelete.postValue(false) }
        return liveDelete
    }
}