package com.example.ventazapas.data.fireStore

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ventazapas.data.model.ResponseShoes
import com.example.ventazapas.data.model.ResponseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

import com.google.android.gms.tasks.OnSuccessListener

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import java.io.File


class FireStoreImp : FireStoreService {
    val mStorage = FirebaseStorage.getInstance().getReference()
    private val fireStore = FirebaseFirestore.getInstance()
    private val liveUser = MutableLiveData<ResponseUser>()
    private val liveShoes = MutableLiveData<ResponseShoes>()
    private val liveDelete = MutableLiveData<Boolean>()
    private val liveAllOffertShoes = MutableLiveData<List<ResponseShoes>>()
    private val liveAllShoes = MutableLiveData<List<ResponseShoes>>()
    private val liveListShoesById = MutableLiveData<List<ResponseShoes>>()
    private val liveImage = MutableLiveData<String>()

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
        fireStore.collection("shoes").whereEqualTo("state_offer", false).get()
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

    fun getImage(image: Uri): MutableLiveData<String> {
        val FilePath: StorageReference =
            mStorage.child("Fotos1")!!.child(image.getLastPathSegment()!!)
        image.let { FilePath.putFile(it) }
            ?.addOnSuccessListener { //Esto seria para descargar su token de enlace y poder acceder a ella
                //Si no lo quieres poner no hace falta

                FilePath.downloadUrl.addOnSuccessListener {
                    Log.d("responseFire", it.toString())
                    liveImage.postValue(it.toString())
                }
            }
        return liveImage
    }

    override fun getListShoesByOffert(): MutableLiveData<List<ResponseShoes>> {
        fireStore.collection("shoes").whereEqualTo("state_offer", true).get().addOnSuccessListener {
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
            liveAllOffertShoes.postValue(list)
        }
        return liveAllOffertShoes
    }

    fun addFavoriteToUser(email: String, id: String, owner: LifecycleOwner) {
        var newIds = mutableListOf<String>()
        getUser(email).observe(owner, {
            if(it.favorite.isNotEmpty()){
            for (i in it.favorite) {
                if (!i.equals("") && !newIds.contains(i)){
                    newIds.add(i)
                }

            }}
            newIds.add(id)
            fireStore.collection("user").document(email).set(
                hashMapOf(
                    "debt" to it.debt,
                    "direction" to it.direction,
                    "dni" to it.dni,
                    "email" to it.email,
                    "favorite" to newIds,
                    "id_edit" to it.id_edit,
                    "name" to it.name,
                    "number" to it.number,
                    "orders" to it.orders,
                    "shopping" to it.shopping,
                    "state_account" to it.state_account,
                    "type" to it.type
                )
            )
        })
    }

    fun deleteFavoriteToUser(email: String, id: String, owner: LifecycleOwner) {
        var newIds2 = mutableListOf<String>()
        getUser(email).observe(owner, {
            newIds2 = it.favorite as MutableList<String>
            if (newIds2.contains(id)) {
                newIds2.remove(id)
            }

            fireStore.collection("user").document(email).set(
                hashMapOf(
                    "debt" to it.debt,
                    "direction" to it.direction,
                    "dni" to it.dni,
                    "email" to it.email,
                    "favorite" to newIds2,
                    "id_edit" to it.id_edit,
                    "name" to it.name,
                    "number" to it.number,
                    "orders" to it.orders,
                    "shopping" to it.shopping,
                    "state_account" to it.state_account,
                    "type" to it.type
                )
            )
        })
    }

    fun getListShoesByIDs(listId: List<String>): MutableLiveData<List<ResponseShoes>> {
        val listTemp = mutableListOf<ResponseShoes>()
        for (i in listId){
            fireStore.collection("shoes").whereEqualTo("id", i).get().addOnSuccessListener{
                for (i in it.documents) {
                    listTemp.add(
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
                liveListShoesById.value = listTemp
            }
        }
        return liveListShoesById
    }
}