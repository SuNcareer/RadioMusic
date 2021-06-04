
package com.test.radioplayer.ui.history

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.colortheme.constants.AppDatabase
import com.test.radioplayer.R
import com.test.radioplayer.adapter.MusicSongAdp
import com.test.radioplayer.databinding.FrgHistoryBinding
import com.test.radioplayer.model.MusicSong
import com.test.radioplayer.ui.base.BaseFrg
import com.test.radioplayer.ui.home.DashboardAct
import com.test.radioplayer.utils.Utils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HistoryFrg : BaseFrg<HistoryVM>(R.layout.frg_history), HistoryV {

    private var frgHistoryBinding: FrgHistoryBinding? = null
    private lateinit var historyVM: HistoryVM
    private lateinit var musicSongAdp: MusicSongAdp
    private lateinit var appDatabase: AppDatabase

    @DelicateCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        frgHistoryBinding = FrgHistoryBinding.inflate(inflater, container, false)
        appDatabase = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, "RadioPlay"
        ).allowMainThreadQueries().build()
        setupViewModel()
        setupUI()

        if (Utils.isOnline(requireContext()))
            callPastSongsAPI()
        else
            retrieveList()

        return frgHistoryBinding!!.root

    }

    override fun onResume() {
        super.onResume()


    }

    private fun setupViewModel() {
        historyVM = ViewModelProvider(this).get(HistoryVM::class.java)
        historyVM.setNavigator(this)
    }

    private fun setupUI() {
        frgHistoryBinding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        musicSongAdp = MusicSongAdp(arrayListOf())
        frgHistoryBinding!!.recyclerView.addItemDecoration(
            DividerItemDecoration(
                frgHistoryBinding!!.recyclerView.context,
                (frgHistoryBinding!!.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        frgHistoryBinding!!.recyclerView.adapter = musicSongAdp
    }

    private fun callPastSongsAPI() {
        frgHistoryBinding!!.progressBar.visibility = View.VISIBLE
        frgHistoryBinding!!.recyclerView.visibility = View.GONE
        historyVM.callPastSongsHistory()
    }

    private fun retrieveList() {
        frgHistoryBinding!!.recyclerView.visibility = View.VISIBLE
        val musicSong = appDatabase.musicSongDao()
        val musicSongsL = musicSong.getAll()
        appDatabase.close()
        if (musicSongsL.isNotEmpty())
            (activity as DashboardAct).setCurrentSong(musicSongsL[0])
        musicSongAdp.apply {
            addMusicSongs(musicSongsL)
            notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        frgHistoryBinding = null
        super.onDestroyView()
    }

    override fun onGetPastSongs(musicSongML: MutableList<MusicSong>) {
        frgHistoryBinding!!.recyclerView.visibility = View.VISIBLE
        frgHistoryBinding!!.progressBar.visibility = View.GONE
        appDatabase.musicSongDao().insertAll(musicSongML)
        retrieveList()
    }

    override fun onSessionExpired(description: String?) {
        appDatabase.close()
        frgHistoryBinding!!.recyclerView.visibility = View.VISIBLE
        frgHistoryBinding!!.progressBar.visibility = View.GONE
        Toast.makeText(activity, R.string.em_no_network, Toast.LENGTH_LONG).show()
    }

    override fun onError(statusCode: Int?, description: String?) {
        appDatabase.close()
        frgHistoryBinding!!.recyclerView.visibility = View.VISIBLE
        frgHistoryBinding!!.progressBar.visibility = View.GONE
        Toast.makeText(activity, R.string.em_no_network, Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): HistoryVM {
        return historyVM
    }

}