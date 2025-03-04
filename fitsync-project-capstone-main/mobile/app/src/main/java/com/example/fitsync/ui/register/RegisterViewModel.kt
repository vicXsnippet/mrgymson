package com.example.fitsync.ui.register

import androidx.lifecycle.ViewModel
import com.example.fitsync.data.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun register(
        firstName: String,
        lastName: String,
        username: String,
        password: String
    ) = userRepository.register(firstName, lastName, username, password)
}