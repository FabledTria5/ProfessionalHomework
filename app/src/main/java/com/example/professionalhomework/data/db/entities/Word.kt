package com.example.professionalhomework.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "words_table")
data class Word(
    @PrimaryKey
    @SerializedName("word")
    val word: String,
    @SerializedName("translation")
    val translation: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("sound")
    val sound: String?
)
