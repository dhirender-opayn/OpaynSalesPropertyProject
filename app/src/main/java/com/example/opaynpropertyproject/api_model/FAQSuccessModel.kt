package com.example.opaynpropertyproject.api_model

data class FAQSuccessModel(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val answer: String,
        val created_at: String,
        val id: Int,
        val question: String,
        val status: Int,
        val updated_at: String
    )
}