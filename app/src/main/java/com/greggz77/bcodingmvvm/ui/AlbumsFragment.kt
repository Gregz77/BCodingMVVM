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
import androidx.recyclerview.widget.LinearLayoutManager
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Album
import com.greggz77.bcodingmvvm.data.viewModels.AlbumViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_users.*

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
            initRecyclerView(albums.toAlbumItems())
            Log.i("BCodingMVVM:MainActivity", albums.toString())
        })
    }

    private fun List<Album>.toAlbumItems(): List<AlbumItem>{

        return this.map {
            AlbumItem(it)
        }
    }

    private fun initRecyclerView(items: List<AlbumItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AlbumsFragment.context)
            adapter = groupAdapter
        }
    }

}