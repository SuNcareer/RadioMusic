
package com.test.radioplayer.ui.base

import androidx.appcompat.app.AppCompatActivity


abstract class BaseAct<V> : AppCompatActivity(), BaseV {
    abstract fun getViewModel(): V


}

