package com.example.fitsync.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitsync.data.ResultState
import com.example.fitsync.data.repository.UserRepository
import com.example.fitsync.data.response.ResponseRiwayatLatihan
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
   private val _dashboardData = MutableLiveData<ResultState<ResponseRiwayatLatihan>>()
   val dashboardData: LiveData<ResultState<ResponseRiwayatLatihan>> = _dashboardData

   fun getDashboardData(id: String) {
      viewModelScope.launch {
         userRepository.getDashboardData(id).observeForever { result ->
            _dashboardData.value = result
         }
      }
   }

   fun getUserId(): LiveData<String> {
      return userRepository.getSession().asLiveData()
   }

   fun logout() {
      viewModelScope.launch {
         userRepository.logout()
      }
   }
}