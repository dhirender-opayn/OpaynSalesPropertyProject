package com.example.opaynpropertyproject.api_model

data class SellPropertyModel(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val created_at: String,
        val id: Int,
        val name: String,
        val options: List<Option>,
        val updated_at: String
    ) {
        data class Option(
            val created_at: String,
            val id: Int,
            val name: String,
            val property_filter_type_id: Int,
            val updated_at: String,
            var flag :Boolean = false
        )
    }
}