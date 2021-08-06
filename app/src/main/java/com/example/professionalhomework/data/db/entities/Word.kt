package com.example.professionalhomework.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_words")
data class Word(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "pronunciation")
    val pronunciation: String?,
)
