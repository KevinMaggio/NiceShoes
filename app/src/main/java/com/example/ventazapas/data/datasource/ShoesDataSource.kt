package com.example.ventazapas.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.ventazapas.data.fireStore.FireStoreImp
import com.example.ventazapas.data.model.Gender
import com.example.ventazapas.data.model.MockShoes
import com.example.ventazapas.data.model.ResponseShoes

class ShoesDataSource  {

    fun getMockShoes(): List<MockShoes> {
        return listOf<MockShoes>(
            MockShoes(
                "street",
                "zapatilla",
                5500,
                false,
                listOf<String>(
                    "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/049/128/products/5361-c64f9293ea27a4445115886263743160-480-0.jpg",
                    "https://d3ugyf2ht6aenh.cloudfront.net/stores/001/049/128/products/4751-f82f6077c4ffccd1f015886263743746-640-0.jpg"
                ),
                "Street",
                "42",
                "Negras",
                Gender.Masculino
            ),
            MockShoes(
                "street",
                "zapatilla",
                6500,
                false,
                listOf<String>(
                    "https://static.dafiti.com.ar/p/filament-1311-297742-1-product.jpg",
                    "https://d3ugyf2ht6aenh.cloudfront.net/stores/234/062/products/img_9193-1-600x6001-3da96e9740b1d6593716122886460862-1024-1024.jpg",
                    "https://d3ugyf2ht6aenh.cloudfront.net/stores/234/062/products/img_9196-600x6001-b9add794de037e6fd716122886461674-1024-1024.jpg"
                ),
                "Street",
                "41",
                "Mostaza",
                Gender.Masculino
            ),
            MockShoes(
                "Lander",
                "zapatillas",
                7000,
                false,
                listOf<String>("https://pbs.twimg.com/media/EUb7jKPXsAAABp5.jpg"),
                "zapatillas lander de tela. deportivas. ",
                "39",
                "Rosa",
                Gender.Femenino
            ),
            MockShoes(
                "Lander",
                "zapatillas",
                7500,
                false,
                listOf<String>("https://http2.mlstatic.com/D_NQ_NP_910572-MLA45882455148_052021-O.jpg"),
                "Zapatillas Lander de tela. femeninas. deportivas",
                "38",
                "Azul",
                Gender.Femenino
            ),
            MockShoes(
                "Lisboa",
                "zapatillas",
                5500,
                false,
                listOf<String>("https://http2.mlstatic.com/D_NQ_NP_757691-MLA40437401764_012020-O.jpg"),
                "Zapatillas Lisboa tela Unisex",
                "39",
                "Negro",
                Gender.Unisex
            )
        )
    }
}