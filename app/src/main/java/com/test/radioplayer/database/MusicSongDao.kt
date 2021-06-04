package com.test.radioplayer.database

import androidx.room.*
import com.test.radioplayer.model.MusicSong

@Dao
interface MusicSongDao {
    @Query("SELECT * FROM MusicSong")
    fun getAll(): List<MusicSong>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(musicSongL: List<MusicSong>)

    @Delete
    fun delete(musicSong: MusicSong)
}