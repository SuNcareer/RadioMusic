package com.test.radioplayer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Songs(

    @ColumnInfo(name = "sid") val sid: String,
    @ColumnInfo(name = "album") val album: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "artist") val artist: String,
    @ColumnInfo(name = "image_url") val image_url: String,
    @ColumnInfo(name = "link_url") val link_url: String,
    @ColumnInfo(name = "preview_url") val preview_url: String,
    @ColumnInfo(name = "played_at") val played_at: String
)
