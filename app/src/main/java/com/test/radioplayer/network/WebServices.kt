package com.test.radioplayer.network

import com.test.radioplayer.model.MusicSong
import retrofit2.Call
import retrofit2.http.*


interface WebServices {

    @GET("935/testapi")
    fun getPastSongs(): Call<MutableList<MusicSong>>
}