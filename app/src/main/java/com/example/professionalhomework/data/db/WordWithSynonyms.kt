package com.example.professionalhomework.data.db

import androidx.room.Embedded
import androidx.room.Relation
import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word

data class WordWithSynonyms(
    @Embedded val word: Word,
    @Relation(
        parentColumn = "word",
        entityColumn = "parentWord"
    )
    val synonyms: List<Synonym>
)
