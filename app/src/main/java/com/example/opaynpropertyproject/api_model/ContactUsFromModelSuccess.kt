package com.example.opaynpropertyproject.api_model

data class ContactUsFromModelSuccess(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val created_at: String,
        val deleted_at: Any,
        val description: String,
        val email: String,
        val id: Int,
        val mobile: String,
        val name: String,
        val status: Int,
        val subject: String,
        val updated_at: String
    )
}