package com.example.opaynpropertyproject.api_model

data class LoginSuccessModel(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val token: String,
        val user: User
    ) {
        data class User(
            val created_at: String,
            val email: String,
            val email_verified_at: String,
            val id: Int,
            val mobile: Long,
            val name: String,
            val profile: Profile,
            val roles: List<Role>,
            val updated_at: String
        ) {
            data class Profile(
                val created_at: String,
                val id: Int,
                val image: Any,
                val updated_at: String,
                val user_id: Int
            )

            data class Role(
                val created_at: String,
                val guard_name: String,
                val id: Int,
                val name: String,
                val pivot: Pivot,
                val updated_at: String
            ) {
                data class Pivot(
                    val model_id: Int,
                    val model_type: String,
                    val role_id: Int
                )
            }
        }
    }
}