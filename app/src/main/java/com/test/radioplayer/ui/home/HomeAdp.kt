/*
 *  * Created by SuNiL PaTeL on 02/06/21 10:46 PM
 */
package com.test.radioplayer.ui.home


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.radioplayer.ui.base.BaseAct
import com.test.radioplayer.ui.history.HistoryFrg
import com.test.radioplayer.ui.station.RadioStationFrg


class HomeAdp(baseAct: BaseAct<Any>, private val tabCount: Int) : FragmentStateAdapter(baseAct) {

    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                RadioStationFrg()
            }
            1 -> {
                HistoryFrg()
            }
            else -> RadioStationFrg()
        }
    }
}
