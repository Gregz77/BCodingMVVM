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
import com.greggz77.bcodingmvvm.data.viewModels.PhotoViewModel
import com.greggz77.bcodingmvvm.util.InjectorUtils
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
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
            photoText1.text = photos.toString()
            Log.i("BCodingMVVM:MainActivity", photos.toString())
        })
    }
}