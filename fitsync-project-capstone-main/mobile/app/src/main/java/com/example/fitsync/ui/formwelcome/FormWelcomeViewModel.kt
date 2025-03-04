package com.example.fitsync.ui.formwelcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fitsync.data.repository.UserRepository

class FormWelcomeViewModel(private val userRepository: UserRepository): ViewModel() {
    fun postBodyMetrik(userId: String, weight:Int, height:Int) = userRepository.postBobyMetrik(userId, weight, height)

    fun getUserId(): LiveData<String> {
        return userRepository.getSession().asLiveData()
    }
}