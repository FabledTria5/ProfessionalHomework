package com.example.professionalhomework.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word

data class WordWithMeanings(
    @Embedded val word: Word,
    @Relation(
        parentColumn = "word",
        entityColumn = "parent_word"
    )
    val meanings: List<Meaning>
)
