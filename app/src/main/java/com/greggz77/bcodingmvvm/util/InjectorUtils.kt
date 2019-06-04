package com.greggz77.bcodingmvvm.util

import android.app.Application
import com.greggz77.bcodingmvvm.data.repositories.AlbumRepository
import com.greggz77.bcodingmvvm.data.repositories.PhotoRepository
import com.greggz77.bcodingmvvm.data.repositories.UserRepository
import com.greggz77.bcodingmvvm.data.viewModels.AlbumViewModelFactory
import com.greggz77.bcodingmvvm.data.viewModels.PhotoViewModelFactory
import com.greggz77.bcodingmvvm.data.viewModels.UserViewModelFactory
import com.greggz77.bcodingmvvm.util.ApiFactory.albumsApi
import com.greggz77.bcodingmvvm.util.ApiFactory.photosApi
import com.greggz77.bcodingmvvm.util.ApiFactory.usersApi


object InjectorUtils {

    fun provideUserViewModelFactory(application: Application): UserViewModelFactory {
        val userRepository = UserRepository.getInstance(
            usersApi
        )
        return UserViewModelFactory(userRepository)
    }

    fun provideAlbumViewModelFactory(application: Application): AlbumViewModelFactory {
        val albumRepository = AlbumRepository.getInstance(
            albumsApi
        )
        return AlbumViewModelFactory(albumRepository)
    }

    fun providePhotoViewModelFactory(application: Application): PhotoViewModelFactory {
        val photoRepository = PhotoRepository.getInstance(
            photosApi
        )
        return PhotoViewModelFactory(photoRepository)
    }
}