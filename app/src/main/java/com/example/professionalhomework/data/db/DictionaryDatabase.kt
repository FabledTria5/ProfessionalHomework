package com.example.professionalhomework.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word

@Database(entities = [Synonym::class, Word::class], version = 6)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
}