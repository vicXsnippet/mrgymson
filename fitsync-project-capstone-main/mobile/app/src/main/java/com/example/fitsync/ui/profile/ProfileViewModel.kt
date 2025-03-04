package com.example.fitsync.ui.profile

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fitsync.data.ResultState
import com.example.fitsync.data.repository.UserRepository
import com.example.fitsync.data.response.UserProfileResponse

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
   private val _userData = MutableLiveData<ResultState<UserProfileResponse>>()
   val userData: LiveData<ResultState<UserProfileResponse>> = _userData

   fun getUserProfile(userId: String) {
      userRepository.getUserProfile(userId).observeForever {
         _userData.value = it
      }
   }

   fun getUserId(): LiveData<String> {
      return userRepository.getSession().asLiveData()
   }
}