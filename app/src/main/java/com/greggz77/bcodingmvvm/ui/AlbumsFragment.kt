package com.greggz77.bcodingmvvm.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.viewModels.AlbumViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import kotlinx.android.synthetic.main.fragment_albums.*

class AlbumsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
    }

    private fun initUi() {

        val factory = InjectorUtils.provideAlbumViewModelFactory(application = Application())
        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(AlbumViewModel::class.java)
        viewModel.getAlbums().observe(this, Observer { albums ->
            albumText.text = albums.toString()
            Log.i("BCodingMVVM:MainActivity", albums.toString())
        })
    }
}