package com.greggz77.bcodingmvvm.data.repositories

import android.util.Log
import com.greggz77.bcodingmvvm.data.apis.AlbumsApi
import com.greggz77.bcodingmvvm.data.models.Album

class AlbumRepository private constructor(
    private val api: AlbumsApi
) : BaseRepository() {

    suspend fun getAlbums(): List<Album> {
        return try {
            safeApiCall(
                call = {api.getAlbums().await()},
                errorMessage = "Error getting albums"
            ) ?: listOf()
        } catch (e: Exception) {
            Log.e("BCodingMVVM:AlbumRepository", "API call failed completely: ${e.stackTrace}")
            Log.e("BCodingMVVM:BaseRepository", "$e")
            listOf()
        }
    }

    companion object {

        @Volatile private var instance: AlbumRepository? = null

        fun getInstance(api: AlbumsApi) =
                instance ?: synchronized(this) {
                    instance ?: AlbumRepository(api).also { instance = it }
                }
    }
}