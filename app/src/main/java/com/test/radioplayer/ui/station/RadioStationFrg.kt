
package com.test.radioplayer.ui.station

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.test.radioplayer.R
import com.test.radioplayer.databinding.FrgStationBinding
import com.test.radioplayer.ui.base.BaseFrg
import com.test.radioplayer.ui.home.DashboardAct
import kotlinx.android.synthetic.main.item_layout.view.*


class RadioStationFrg : BaseFrg<Any>(R.layout.frg_station) {

    private var frgStationBinding: FrgStationBinding? = null
    private val PERMISSION_REQUEST_CODE = 200


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        frgStationBinding = FrgStationBinding.inflate(inflater, container, false)

        requestPermissionAndContinue()



        return frgStationBinding!!.root

    }

    override fun onResume() {
        super.onResume()
        val musicSong = (activity as DashboardAct).getCurrentSong()
        frgStationBinding?.tvTitle?.text = musicSong?.name+""
        frgStationBinding?.tvAlbum?.text = musicSong?.album+""
        frgStationBinding?.tvArtist?.text = musicSong?.artist+""
        
        Glide.with(this)
            .load(musicSong?.image_url)
            .into( frgStationBinding?.imageViewAvatar!!)
    }
    override fun onDestroyView() {
        frgStationBinding = null
        super.onDestroyView()
    }

    override fun onSessionExpired(description: String?) {
        TODO("Not yet implemented")
    }

    override fun onError(statusCode: Int?, description: String?) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
              )
    }

     fun requestPermissionAndContinue() {
        if (checkPermission()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    && ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    )

            ) {
                val alertBuilder = AlertDialog.Builder(requireActivity())
                alertBuilder.setCancelable(true)
                alertBuilder.setTitle(getString(R.string.permission_necessary))
                alertBuilder.setMessage(R.string.storage_permission_is_necessary_to_wrote_event)
                alertBuilder.setPositiveButton(
                        android.R.string.yes
                ) { _, _ ->
                    requestPermissions(
                            arrayOf(
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                            ),
                            PERMISSION_REQUEST_CODE
                    )
                }
                val alert = alertBuilder.create()
                alert.show()
                Log.e("", "permission denied, show dialog")
            } else {
                requestPermissions(
                        arrayOf(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ), PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String?>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.isNotEmpty() && grantResults.isNotEmpty()) {
                var flag = true
                for (i in grantResults.indices) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false
                    }
                }
                if (!flag) {
                    requestPermissionAndContinue()
                }
            } else {
                requestPermissionAndContinue()
            }
        }
    }

    override fun getViewModel(): Any {
        TODO("Not yet implemented")
    }


}