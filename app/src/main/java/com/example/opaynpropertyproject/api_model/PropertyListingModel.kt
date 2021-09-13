package com.example.opaynpropertyproject.api_model

data class PropertyListingModel(
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val address: String,
        val city: Int,
        val created_at: String,
        val description: String,
        val id: Int,
        val image: Any,
        val list_type: String,
        val name: String,
        val pincode: Int,
        val price: Int,
        val profile: Any,
        val property_type: String,
        val state: Int,
        val status: Int,
        val updated_at: String,
        val user_id: Int
    )
}