package com.example.professionalhomework.data.network.model


import com.google.gson.annotations.SerializedName

data class Phonetic(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("text")
    val text: String
)