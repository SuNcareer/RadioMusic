package com.test.radioplayer.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseVM<N>(application: Application): AndroidViewModel(application) {

    private var mNavigator: N? = null

    fun getNavigator(): N ?{
        return mNavigator
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }
}