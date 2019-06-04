package com.greggz77.bcodingmvvm.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val title: String,
    val userId: Int
)