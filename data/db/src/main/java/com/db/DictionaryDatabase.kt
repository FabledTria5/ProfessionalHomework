package com.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.db.daos.WordsDao
import com.db.models.SynonymEntity
import com.db.models.WordEntity

@Database(entities = [SynonymEntity::class, WordEntity::class], version = 6)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
}