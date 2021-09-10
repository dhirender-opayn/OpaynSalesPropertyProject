package com.example.opaynpropertyproject.api_model

data class AddPropertyModel(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val address: String,
        val amenities: List<Any>,
        val city: Int,
        val created_at: String,
        val description: Any,
        val id: Int,
        val images: List<Any>,
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