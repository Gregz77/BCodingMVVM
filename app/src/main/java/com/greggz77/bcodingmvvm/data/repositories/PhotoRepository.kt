package com.greggz77.bcodingmvvm.data.repositories

import android.util.Log
import com.greggz77.bcodingmvvm.data.apis.PhotosApi
import com.greggz77.bcodingmvvm.data.models.Photo

class PhotoRepository private constructor(
    private val api: PhotosApi
) : BaseRepository() {

    suspend fun getPhotos(): List<Photo> {
        return try {
            safeApiCall(
                call = {api.getPhotos().await()},
                errorMessage = "Error getting photos"
            ) ?: listOf()
        } catch (e: Exception) {
            Log.e("BCodingMVVM:PhotoRepository", "API call failed completely: ${e.stackTrace}")
            Log.e("BCodingMVVM:BaseRepository", "$e")
            listOf()
        }
    }

    companion object {

        @Volatile private var instance: PhotoRepository? = null

        fun getInstance(api: PhotosApi) =
                instance ?: synchronized(this) {
                    instance ?: PhotoRepository(api).also { instance = it }
                }
    }
}