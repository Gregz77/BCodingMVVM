package com.greggz77.bcodingmvvm.data.repositories

import android.util.Log
import com.greggz77.bcodingmvvm.data.apis.UsersApi
import com.greggz77.bcodingmvvm.data.models.User

class UserRepository private constructor(
    private val api: UsersApi
    //private val dao: UsersDao
) : BaseRepository() {

    suspend fun getUsers(): List<User> {
        return try {
            safeApiCall(
                call = {api.getUsers().await()},
                errorMessage = "Error getting users"
            ) ?: listOf()
        } catch (e: Exception) {
            Log.e("BCodingMVVM:UserRepository", "API call failed completely: ${e.stackTrace}")
            Log.e("BCodingMVVM:BaseRepository", "$e")
            listOf()
        }
    }

    companion object {

        @Volatile private var instance: UserRepository? = null

        fun getInstance(api: UsersApi) =
                instance ?: synchronized(this) {
                    instance ?: UserRepository(api).also { instance = it }
                }
    }
}