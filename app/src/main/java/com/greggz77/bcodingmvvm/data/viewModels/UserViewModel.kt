package com.greggz77.bcodingmvvm.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greggz77.bcodingmvvm.data.models.User
import com.greggz77.bcodingmvvm.data.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel (private val userRepository: UserRepository): ViewModel() {

    private val usersLiveData = MutableLiveData<List<User>>()
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun getUsers(): MutableLiveData<List<User>> {
        scope.launch {
            usersLiveData.postValue(userRepository.getUsers())
        }
        return usersLiveData
    }
}