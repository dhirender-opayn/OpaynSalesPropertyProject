package com.example.opaynpropertyproject.api_model

data class CustomerHomeModel(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val current_page: Int,
        val `data`: List<Data>,
        val first_page_url: String,
        val from: Int,
        val last_page: Int,
        val last_page_url: String,
        val links: List<Link>,
        val next_page_url: Any,
        val path: String,
        val per_page: Int,
        val prev_page_url: Any,
        val to: Int,
        val total: Int
    ) {
        data class Data(
            val address: String,
            val city: Int,
            val created_at: String,
            val description: String,
            val id: Int,
            val image: Image,
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
            data class Image(
                val created_at: String,
                val id: Int,
                val image: String,
                val property_id: Int,
                val updated_at: String
            )

            data class Profile(
                val age: Int,
                val area: String,
                val area_type: Int,
                val bath_rooms: Int,
                val bed_rooms: Int,
                val created_at: String,
                val entrance: Int,
                val furnishing: Int,
                val id: Int,
                val possession: Int,
                val posted_by: Int,
                val property_id: Int,
                val status: Int,
                val updated_at: String,
                val user_id: Int
            )
        }

        data class Link(
            val active: Boolean,
            val label: String,
            val url: Any
        )
    }
}