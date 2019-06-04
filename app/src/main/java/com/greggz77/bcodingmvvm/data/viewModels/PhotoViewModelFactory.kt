package com.greggz77.bcodingmvvm.data.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greggz77.bcodingmvvm.data.repositories.PhotoRepository

class PhotoViewModelFactory(private val photoRepository: PhotoRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(photoRepository) as T
    }
}