/*
 *  * Created by SuNiL PaTeL on 01/06/21 9:20 PM
 */
package com.test.radioplayer.ui.home

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.ads.LoadAdError
import com.google.android.material.tabs.TabLayout
import com.test.radioplayer.R
import com.test.radioplayer.databinding.DashboardActBinding
import com.test.radioplayer.model.MusicSong
import com.test.radioplayer.ui.base.BaseAct
import java.io.IOException


class DashboardAct : BaseAct<Any>() {
    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var dashboardActBinding: DashboardActBinding
    private var mediaPlayer: MediaPlayer? = null
    private var musicSong: MusicSong? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardActBinding = DashboardActBinding.inflate(layoutInflater)
        setContentView(dashboardActBinding.root)


        dashboardActBinding.pbLoading.visibility = View.GONE
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.e("Ads", adError?.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.e("Ads", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
        dashboardActBinding.mbStart.setOnClickListener {


            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            dashboardActBinding.pbLoading.visibility = View.VISIBLE
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer?.setAudioAttributes(
                AudioAttributes
                    .Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            volumeControlStream = AudioManager.STREAM_MUSIC

            try {
                mediaPlayer?.setDataSource("https://rfcmedia.streamguys1.com/70hits.aac")
                mediaPlayer?.prepareAsync()
                mediaPlayer?.setOnPreparedListener { mp ->
                    mediaPlayer = mp
                    dashboardActBinding.pbLoading.visibility = View.GONE
                    dashboardActBinding.mbStart.visibility = View.GONE
                    dashboardActBinding.mbPause.visibility = View.VISIBLE
                    mediaPlayer?.start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        dashboardActBinding.mbPause.setOnClickListener {
            dashboardActBinding.mbStart.visibility = View.VISIBLE
            dashboardActBinding.mbPause.visibility = View.GONE
            if (mediaPlayer != null && mediaPlayer?.isPlaying == true)
                mediaPlayer?.pause()
        }


        dashboardActBinding.tlJobs.tabTextColors = ContextCompat.getColorStateList(
            this,
            R.color.primary
        )
        dashboardActBinding.tlJobs.setSelectedTabIndicatorColor(
            ContextCompat.getColor(
                this,
                R.color.accent
            )
        )

        dashboardActBinding.tlJobs.addTab(
            dashboardActBinding.tlJobs.newTab().setText(resources.getString(R.string.station))
        )
        dashboardActBinding.tlJobs.addTab(
            dashboardActBinding.tlJobs.newTab().setText(resources.getString(R.string.history))
        )
        dashboardActBinding.tlJobs.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = HomeAdp(this, dashboardActBinding.tlJobs.tabCount)
        dashboardActBinding.vpJobs.adapter = adapter

        dashboardActBinding.vpJobs.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dashboardActBinding.tlJobs.selectTab(dashboardActBinding.tlJobs.getTabAt(position))

            }

        })

        dashboardActBinding.vpJobs.currentItem = 1

//        val vpPageChangeListener = VpPageChangeListener()
//        todoFrgBinding.vpJobs.registerOnPageChangeCallback(vpPageChangeListener)

//

        dashboardActBinding.tlJobs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                dashboardActBinding.vpJobs.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })


    }

    fun setCurrentSong(musicSong: MusicSong) {
        this.musicSong = musicSong
        dashboardActBinding.tvTitle.text = musicSong?.name + ""
        dashboardActBinding.tvAlbum.text = musicSong?.album + ""
        dashboardActBinding.tvArtistName.text = musicSong?.artist + ""
    }

    fun getCurrentSong(): MusicSong? {
        return musicSong
    }

    override fun onSessionExpired(description: String?) {
        TODO("Not yet implemented")
    }

    override fun onError(statusCode: Int?, description: String?) {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): Any {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
    }
}