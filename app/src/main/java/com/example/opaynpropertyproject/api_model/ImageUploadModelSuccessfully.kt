package com.example.opaynpropertyproject.api_model

data class ImageUploadModelSuccessfully(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val image: Image
    ) {
        data class Image(
            val created_at: String,
            val id: Int,
            val image: String,
            val property_id: String,
            val updated_at: String
        )
    }
}