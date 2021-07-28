package com.example.professionalhomework.data.network.model


import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("definitions")
    val definitions: List<Definition>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String
)