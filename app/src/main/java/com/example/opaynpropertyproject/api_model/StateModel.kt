package com.example.opaynpropertyproject.api_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class StateModel(
    val `data`: List<Data>,
    val message: String
)  {

    data class Data(
        val id: Int,
        val name: String
    )
}
