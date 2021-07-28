package com.example.professionalhomework.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word

@Database(entities = [Meaning::class, Word::class], version = 5)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
}