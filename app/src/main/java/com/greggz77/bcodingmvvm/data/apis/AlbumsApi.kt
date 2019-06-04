package com.greggz77.bcodingmvvm.data.apis

import com.greggz77.bcodingmvvm.data.models.Album
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface AlbumsApi {

    @GET("/albums")
    fun getAlbums(): Deferred<Response<List<Album>>>
}