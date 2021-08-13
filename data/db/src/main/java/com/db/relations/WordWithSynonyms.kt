package com.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.db.models.SynonymEntity
import com.db.models.WordEntity

data class WordWithSynonyms(
    @Embedded val word: WordEntity,
    @Relation(
        parentColumn = "word",
        entityColumn = "parent_word"
    )
    val synonyms: List<SynonymEntity>
)
