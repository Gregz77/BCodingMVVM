package com.greggz77.bcodingmvvm.util

import android.app.Application
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.greggz77.bcodingmvvm.data.apis.AlbumsApi
import com.greggz77.bcodingmvvm.data.apis.PhotosApi
import com.greggz77.bcodingmvvm.data.apis.UsersApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestUtils private constructor() : Application() {

    companion object {
        fun doRequest(context: Context,
                      url: String,
                      method: String = "GET",
                      callback: Function<Any>,
                      queryParams: String? = null,
                      body: String? = null) {
            Log.i("BCodingMVVM", "Executing REST call for users")
            Log.i("BCodingMVVM", "$url, $method")
            val jsonArrayRequest = JsonArrayRequest(url,
                Response.Listener<JSONArray> { response -> Log.i("BCodingMVVM", response.toString())},
                Response.ErrorListener {Log.e("BCodingMVVM", "ups...")})
            val requestQueue = Volley.newRequestQueue(context)
            requestQueue.add(jsonArrayRequest)
        }
    }
}

object ApiFactory {

    private val authInterceptor = Interceptor {chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }


    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val usersApi : UsersApi = retrofit().create(UsersApi::class.java)
    val albumsApi : AlbumsApi = retrofit().create(AlbumsApi::class.java)
    val photosApi : PhotosApi = retrofit().create(PhotosApi::class.java)
}

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}