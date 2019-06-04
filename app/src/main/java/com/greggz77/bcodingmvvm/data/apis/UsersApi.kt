package com.greggz77.bcodingmvvm.data.apis

import com.greggz77.bcodingmvvm.data.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    fun getUsers(): Deferred<Response<List<User>>>
}