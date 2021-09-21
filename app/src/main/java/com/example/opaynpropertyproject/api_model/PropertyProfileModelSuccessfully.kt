package com.example.opaynpropertyproject.api_model

data class PropertyProfileModelSuccessfully(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val address: String,
        val amenities: List<Int>,
        val city: Int,
        val created_at: String,
        val description: Any,
        val id: Int,
        val images: List<Any>,
        val list_type: String,
        val name: String,
        val pincode: Int,
        val price: Int,
        val profile: Profile,
        val property_type: String,
        val state: Int,
        val status: Int,
        val updated_at: String,
        val user_id: Int
    ) {
        data class Profile(
            val age: Any,
            val area: String,
            val area_type: Any,
            val bath_rooms: Any,
            val bed_rooms: Any,
            val created_at: String,
            val entrance: Any,
            val furnishing: Any,
            val id: Int,
            val possession: Any,
            val posted_by: Any,
            val property_id: Int,
            val status: Int,
            val updated_at: String,
            val user_id: Int
        )
    }
}