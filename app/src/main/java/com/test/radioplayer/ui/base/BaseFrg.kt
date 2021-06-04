/*
 *  * Created by SuNiL PaTeL on 01/06/21 1:15 AM
 */
package com.test.radioplayer.ui.base


import android.view.View
import androidx.fragment.app.Fragment


abstract class BaseFrg<V>(layoutID : Int) : Fragment() , BaseV, View.OnClickListener {
    abstract fun getViewModel(): V
}

