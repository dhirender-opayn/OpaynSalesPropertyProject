package com.example.opaynpropertyproject.api_model

import android.os.Parcelable
 import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuccessSignupModel(
    val `data`: Data,
    val message: String
): Parcelable {
    data class Data(
        val user: User
    )
    {
        data class User(
            val created_at: String,
            val email: String,
            val id: Int,
            val name: String,
            val updated_at: String
        )
    }
}