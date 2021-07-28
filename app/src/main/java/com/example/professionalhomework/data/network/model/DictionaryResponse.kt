package com.example.professionalhomework.data.network.model

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("phonetics")
    val phonetics: List<Phonetic>,
    @SerializedName("word")
    val word: String
)