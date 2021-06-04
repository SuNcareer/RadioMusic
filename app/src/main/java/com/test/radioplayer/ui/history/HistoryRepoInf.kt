package com.test.radioplayer.ui.history

import androidx.lifecycle.LiveData
import com.test.radioplayer.model.MusicSong

interface HistoryRepoInf {
    fun getPastSongs(): LiveData<MutableList<MusicSong>>
}