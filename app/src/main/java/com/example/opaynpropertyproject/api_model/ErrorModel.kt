package com.example.opaynpropertyproject.api_model

data class ErrorModel(
    val message: String,
    val messages: Messages
) {
    data class Messages(
        val email: List<String>
    )
}