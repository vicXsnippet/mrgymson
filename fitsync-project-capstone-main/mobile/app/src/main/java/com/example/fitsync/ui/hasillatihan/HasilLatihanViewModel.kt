package com.example.fitsync.ui.hasillatihan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.data.model.LatihanRequest
import com.example.fitsync.data.repository.UserRepository

class HasilLatihanViewModel(private val userRepository: UserRepository) : ViewModel() {
   fun addCompleteMovement(
      userId: String,
      requestBody: LatihanRequest
   ) = userRepository.addCompleteMovement(
      userId,
      requestBody
   )

   fun getUserId(): LiveData<String> {
      return userRepository.getSession().asLiveData()
   }
}