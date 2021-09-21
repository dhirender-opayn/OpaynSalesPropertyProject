package com.example.opaynpropertyproject.api_model

data class GetProfileModel(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val user: User
    ) {
        data class User(
            val created_at: String,
            val email: String,
            val email_verified_at: String,
            val id: Int,
            val mobile: Any,
            val name: String,
            val profile: Profile,
            val updated_at: String
        ) {
            data class Profile(
                val created_at: String,
                val id: Int,
                val image: Any,
                val updated_at: String,
                val user_id: Int
            )
        }
    }
}