package com.example.professionalhomework.data.network.model

import com.google.gson.annotations.SerializedName

data class DictionaryResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("text")
    val text: String
)