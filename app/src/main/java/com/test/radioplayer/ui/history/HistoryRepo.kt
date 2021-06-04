package com.test.radioplayer.ui.history


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.content.Context
import android.text.TextUtils
import com.test.radioplayer.model.MusicSong
import com.test.radioplayer.network.WebServices
import com.test.radioplayer.network.WsWaClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.util.*

class HistoryRepo(var context: Context) :  HistoryRepoInf,Callback<MutableList<MusicSong>> {

    private var liveData :MutableLiveData<MutableList<MusicSong>>
    private var webService: WebServices


    init {
        webService= WsWaClient.getWsWaClient(context)
        liveData = MutableLiveData()
    }

    override fun onResponse(call: Call<MutableList<MusicSong>>, response: Response<MutableList<MusicSong>>) {
        if (response.isSuccessful && response.code() == 200) {
            liveData.value = response.body()
        }
        else
        {
            liveData.value = arrayListOf()
        }
    }

    override fun onFailure(call: Call<MutableList<MusicSong>>, t: Throwable) {
        if(!TextUtils.isEmpty(t.message)) {
            if(t is ConnectException){
                liveData.value = arrayListOf()
            }else {
                liveData.value = arrayListOf()
            }
        }else{
            liveData.value = arrayListOf()}
    }

    override fun getPastSongs(): LiveData<MutableList<MusicSong>> {
        webService = WsWaClient.getWsWaClient(context)
        webService.getPastSongs().enqueue(this)
        return liveData
    }
}