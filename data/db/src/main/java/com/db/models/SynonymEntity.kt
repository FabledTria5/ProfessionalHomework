package com.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "synonyms_table")
data class SynonymEntity(
    @ColumnInfo(name = "parent_word")
    val parentWord: String,
    @PrimaryKey
    @ColumnInfo(name = "child_word")
    val childWord: String,
    @ColumnInfo(name = "child_image")
    val childImage: String,
    @ColumnInfo(name = "child_translation")
    val childTranslation: String
)
