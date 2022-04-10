package com.example.ventazapas.data.model

data class ResponseUser(
    val debt: Int,
    val direction: String,
    val dni: String,
    val email: String,
    val favorite: List<String>,
    val id_edit: Int,
    val name: String,
    val number: String,
    val orders: List<String>,
    val shopping: List<String>,
    val state_account: Int,
    val type: String
)