package com.test.radioplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.radioplayer.R
import com.test.radioplayer.model.MusicSong
import kotlinx.android.synthetic.main.item_layout.view.*


class MusicSongAdp(private val musicSongAL: ArrayList<MusicSong>) : RecyclerView.Adapter<MusicSongAdp.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(song: MusicSong) {
            itemView.apply {
                textViewTitle.text = song.name
                textViewArtistName.text = song.artist
                Glide.with(imageViewAvatar.context)
                    .load(song.image_url)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = musicSongAL.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(musicSongAL[position])
    }

    fun addMusicSongs(musicSongL: List<MusicSong>) {
        this.musicSongAL.apply {
            clear()
            addAll(musicSongL)
        }

    }
}