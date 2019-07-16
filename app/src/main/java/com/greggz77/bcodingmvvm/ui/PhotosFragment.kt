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
import androidx.recyclerview.widget.GridLayoutManager
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Photo
import com.greggz77.bcodingmvvm.data.viewModels.PhotoViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_recview_toolbar.*

class PhotosFragment : Fragment() {

    private val args: PhotosFragmentArgs by this.navArgs()

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

        val factory = InjectorUtils.providePhotoViewModelFactory(application = Application())
        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(PhotoViewModel::class.java)
        viewModel.getPhotos().observe(this, Observer { photos ->
            initRecyclerView(photos.toPhotoItems())
            Log.i("BCodingMVVM:MainActivity", photos.toString())
            group_loading.visibility = View.GONE
        })
    }

    private fun List<Photo>.toPhotoItems(): List<PhotoItem> {

        return this.filter {
            it.albumId == args.idAlbum
        }.map {
            PhotoItem(it, it.id)
        }
    }

    private fun initRecyclerView(items: List<PhotoItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
            spanCount = 2
        }
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@PhotosFragment.context, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }
        groupAdapter.setOnItemClickListener { item, view ->
            (item as? PhotoItem)?.let {
                showFullScreenImg(it.id, view)
            }
        }
    }

    private fun showFullScreenImg(idPhoto: Int, view: View) {
        val actionFullScreenImg = PhotosFragmentDirections.actionPhotosFragmentToFullScreenFragment(idPhoto)
        Navigation.findNavController(view).navigate(actionFullScreenImg)
    }
}