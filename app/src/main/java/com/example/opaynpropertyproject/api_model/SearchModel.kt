package com.example.opaynpropertyproject.api_model

data class SearchModel(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val current_page: Int,
        val `data`: List<Any>,
        val first_page_url: String,
        val from: Any,
        val last_page: Int,
        val last_page_url: String,
        val links: List<Link>,
        val next_page_url: Any,
        val path: String,
        val per_page: Int,
        val prev_page_url: Any,
        val to: Any,
        val total: Int
    ) {
        data class Link(
            val active: Boolean,
            val label: String,
            val url: Any
        )
    }
}