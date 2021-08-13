package com.example.professionalhomework.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "synonyms_table")
data class Synonym(
    @SerializedName("parent_word")
    val parentWord: String,
    @PrimaryKey
    @SerializedName("child_word")
    val childWord: String,
    @SerializedName("child_image")
    val childImage: String,
    @SerializedName("child_sound")
    val childSound: String?,
    @SerializedName("child_sound")
    val childTranslation: String
)
