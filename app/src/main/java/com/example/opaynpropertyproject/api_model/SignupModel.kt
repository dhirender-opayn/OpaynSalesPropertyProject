package com.example.opaynpropertyproject.api_model

data class SignupModel(
    val message: List<String>,
    val messages: Messages,

) {
    data class Messages(
        val agree: List<String>,
        val email: List<String>,
        val name: List<String>,
        val password: List<String>
    )

}