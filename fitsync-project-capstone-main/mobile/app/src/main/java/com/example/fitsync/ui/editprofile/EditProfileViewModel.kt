package com.example.fitsync.ui.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fitsync.data.model.EditProfileRequest
import com.example.fitsync.data.repository.UserRepository

class EditProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
   fun editProfile(userId: String, userBody: EditProfileRequest) =
      userRepository.editProfile(userId, userBody)


   fun getUserId(): LiveData<String> {
      return userRepository.getSession().asLiveData()
   }
}