package com.example.opaynpropertyproject.api_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SellPropertyModel(
    val `data`: List<Data>,
    val message: String
):Parcelable {
    @Parcelize
    data class Data(
        val created_at: String,
        val id: Int,
        val name: String,
        val options: List<Option>,
        val updated_at: String
    ):Parcelable {
        @Parcelize
        data class Option(
            val created_at: String,
            val id: Int,
            val name: String,
            val property_filter_type_id: Int,
            val updated_at: String,
            var flag :Boolean = false
        ):Parcelable
    }
}