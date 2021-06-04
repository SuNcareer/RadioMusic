package com.example.colortheme.constants

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.radioplayer.database.MusicSongDao
import com.test.radioplayer.model.MusicSong

@Database(entities = arrayOf(MusicSong::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicSongDao(): MusicSongDao
}