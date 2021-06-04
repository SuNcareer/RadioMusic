package com.test.radioplayer.ui.history

import com.test.radioplayer.model.MusicSong
import com.test.radioplayer.ui.base.BaseV


interface HistoryV: BaseV {
    fun onGetPastSongs(musicSongML: MutableList<MusicSong>)

}