package com.test.radioplayer.ui.history

import android.app.Application
import com.test.radioplayer.ui.base.BaseVM

class HistoryVM(application : Application) : BaseVM<HistoryV>(application) {

    fun callPastSongsHistory() {
        val historyRepo = HistoryRepo(getApplication())
        historyRepo.getPastSongs().observe(getNavigator() as HistoryFrg, {
            getNavigator()?.onGetPastSongs(it)
        })
    }
}