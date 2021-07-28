package com.example.professionalhomework.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_words_meanings")
data class Meaning(
    @ColumnInfo(name = "parent_word")
    var parentWord: String,
    @ColumnInfo(name = "partOfSpeech")
    var partOfSpeech: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "definition")
    var definition: String
)