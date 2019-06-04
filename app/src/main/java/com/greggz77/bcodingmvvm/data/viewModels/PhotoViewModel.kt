package com.greggz77.bcodingmvvm.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greggz77.bcodingmvvm.data.models.Photo
import com.greggz77.bcodingmvvm.data.repositories.PhotoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotoViewModel (private val photoRepository: PhotoRepository): ViewModel() {

    private val photosLiveData = MutableLiveData<List<Photo>>()
    private val parentJob = Job()
    private val coroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    fun getPhotos(): MutableLiveData<List<Photo>> {
        scope.launch {
            photosLiveData.postValue(photoRepository.getPhotos())
        }
        return photosLiveData
    }
}