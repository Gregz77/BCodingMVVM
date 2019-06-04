package com.greggz77.bcodingmvvm.data.apis

import com.greggz77.bcodingmvvm.data.models.Photo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PhotosApi {

    @GET("/photos")
    fun getPhotos(): Deferred<Response<List<Photo>>>
}