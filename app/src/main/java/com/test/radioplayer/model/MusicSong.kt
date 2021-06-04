package com.test.radioplayer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MusicSong")
data class MusicSong(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=1,

    @ColumnInfo(name = "sid")
    @SerializedName("sid")
    val sid: String="",

    @ColumnInfo(name = "album")
    @SerializedName("album")
    val album: String="",

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String="",

    @ColumnInfo(name = "artist")
    @SerializedName("artist")
    val artist: String="",

    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    val image_url: String="",

    @ColumnInfo(name = "link_url")
    @SerializedName("link_url")
    val link_url: String="",

    @ColumnInfo(name = "preview_url")
    @SerializedName("preview_url")
    val preview_url: String="",

    @ColumnInfo(name = "played_at")
    @SerializedName("played_at")
    val played_at: String=""
)
