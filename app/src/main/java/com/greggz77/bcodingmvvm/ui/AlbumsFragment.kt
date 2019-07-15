package com.greggz77.bcodingmvvm.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Album
import com.greggz77.bcodingmvvm.data.viewModels.AlbumViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_recview_toolbar.*

class AlbumsFragment : Fragment() {

    private val args: AlbumsFragmentArgs by this.navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recview_toolbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = view.findNavController()
        view.findViewById<Toolbar>(R.id.my_toolbar).setupWithNavController(navController)
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
            group_loading.visibility = View.GONE

        })
    }

    private fun List<Album>.toAlbumItems(): List<AlbumItem>{

        Log.i("args-----------------", args.toString())
        return this.filter {
            it.userId == args.idUser
        }.map {
            AlbumItem(it, it.id)
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

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? AlbumItem)?.let {
                showPhotoGrid(it.id, view)
            }
        }
    }

    private fun showPhotoGrid(idAlbum: Int, view: View) {

        val actionPhotoGrid = AlbumsFragmentDirections.actionAlbumsFragmentToPhotosFragment2(idAlbum)
        Navigation.findNavController(view).navigate(actionPhotoGrid)
    }
}