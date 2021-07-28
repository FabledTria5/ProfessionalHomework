package com.example.professionalhomework.data.network.model


import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String,
    @SerializedName("synonyms")
    val synonyms: List<String>
)