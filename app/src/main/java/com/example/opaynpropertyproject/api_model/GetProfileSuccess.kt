package com.example.opaynpropertyproject.api_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetProfileSuccess(
    val `data`: Data,
    val message: String
) : Parcelable {
    @Parcelize
    data class Data(
        val user: User
    ) : Parcelable {
        @Parcelize
        data class User(
            val created_at: String,
            val email: String,
            val email_verified_at: String,
            val id: Int,
            val mobile: String?=null,
            val name: String,
            val profile: Profile,
            val updated_at: String
        ) : Parcelable {
            @Parcelize
            data class Profile(
                val created_at: String,
                val id: Int,
                val image: String?=null,
                val updated_at: String,
                val user_id: Int
            ) : Parcelable
        }
    }
}