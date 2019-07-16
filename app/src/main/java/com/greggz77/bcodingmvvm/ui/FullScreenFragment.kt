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
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.greggz77.bcodingmvvm.R
import com.greggz77.bcodingmvvm.data.models.Photo
import com.greggz77.bcodingmvvm.data.viewModels.PhotoViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_recview_toolbar.*

class FullScreenFragment : Fragment() {

    private val args: FullScreenFragmentArgs by this.navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recview_toolbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = view.findNavController()
        view.findViewById<Toolbar>(R.id.my_toolbar).setupWithNavController(navController)
        Log.i("Args-------------------------------------", "$args")
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
            initView(photos.toFullScrPhotoItem())
            Log.i("BCodingMVVM:MainActivity", photos.toString())
            group_loading.visibility = View.GONE
        })
    }

    private fun List<Photo>.toFullScrPhotoItem(): List<FullScrPhotoItem> {

        return this.filter {
            it.id == args.idPhoto
        }.map {
            FullScrPhotoItem(it, it.id)
        }
    }

    private fun initView(items: List<FullScrPhotoItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FullScreenFragment.context)
            adapter = groupAdapter
        }
    }
}