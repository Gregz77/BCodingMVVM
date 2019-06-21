package com.greggz77.bcodingmvvm.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greggz77.bcodingmvvm.data.models.Album
import com.greggz77.bcodingmvvm.data.repositories.AlbumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AlbumViewModel (private val albumRepository: AlbumRepository): ViewModel() {

    private val albumsLiveData = MutableLiveData<List<Album>>()
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun getAlbums(): MutableLiveData<List<Album>> {
        scope.launch {
            albumsLiveData.postValue(albumRepository.getAlbums())
        }
        return albumsLiveData
    }
}