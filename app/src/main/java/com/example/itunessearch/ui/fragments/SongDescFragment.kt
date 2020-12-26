package com.example.itunessearch.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.itunessearch.MainActivity
import com.example.itunessearch.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SongDescFragment : Fragment(R.layout.fragment_song_desc) {

lateinit var viewModel: SearchViewModel
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var play: MaterialButton
//val args: SongDescFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    viewModel=(activity as MainActivity).viewModel
        val imgTrack = view.findViewById<ImageView>(R.id.img_Track)
        val trackName = view.findViewById<TextView>(R.id.txt_Track)
        val artistName = view.findViewById<TextView>(R.id.txt_ArtistName)
        val collectionName = view.findViewById<TextView>(R.id.txt_CollectionName)
        val genreName = view.findViewById<TextView>(R.id.txt_GenreName)
        val releaseDate = view.findViewById<TextView>(R.id.txt_ReleaseDate)
        play = view.findViewById<MaterialButton>(R.id.btn_Play)
        play.visibility = View.INVISIBLE
        val fab = view.findViewById<FloatingActionButton>(R.id.fabFavourite)


        val args = arguments?.let { SongDescFragmentArgs.fromBundle(it) }
        val song= args?.song
        trackName.text = song?.trackName
        artistName.text = song?.artistName
        collectionName.text = song?.collectionName
        releaseDate.text = song?.releaseDate
        genreName.text = song?.primaryGenreName
        Glide.with(this).load(song?.artworkUrl100).into(imgTrack)

    }





}