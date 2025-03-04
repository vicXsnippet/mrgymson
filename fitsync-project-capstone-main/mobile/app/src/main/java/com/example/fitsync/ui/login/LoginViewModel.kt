package com.example.fitsync.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitsync.data.repository.UserRepository
import com.example.fitsync.data.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun login(username:String, password:String) = userRepository.login(username, password)

    fun saveSession(user: LoginResponse) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

    fun getUserId(): Flow<String> {
        return userRepository.getSession()
    }
    fun getBodyMetrik(userId: String) = userRepository.getBodyMetrik(userId)
}