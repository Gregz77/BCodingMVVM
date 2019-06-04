package com.greggz77.bcodingmvvm.data.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greggz77.bcodingmvvm.data.repositories.AlbumRepository

class AlbumViewModelFactory(private val albumRepository: AlbumRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumViewModel(albumRepository) as T
    }
}