package com.test.radioplayer.ui.base


interface BaseV {
    fun onSessionExpired(description: String?)
    fun onError(statusCode : Int?,description: String?)
}