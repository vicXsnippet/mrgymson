package com.example.fitsync.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitsync.data.repository.UserRepository
import com.example.fitsync.di.Injection
import com.example.fitsync.ui.dashboard.MainViewModel
import com.example.fitsync.ui.editprofile.EditProfileViewModel
import com.example.fitsync.ui.formwelcome.FormWelcomeViewModel
import com.example.fitsync.ui.hasillatihan.HasilLatihanViewModel
import com.example.fitsync.ui.login.LoginViewModel
import com.example.fitsync.ui.profile.ProfileViewModel
import com.example.fitsync.ui.register.RegisterViewModel

class ViewModelFactory(private val userRepository: UserRepository) :
   ViewModelProvider.NewInstanceFactory() {
   @Suppress("UNCHECKED_CAST")
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return when {
         modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
            RegisterViewModel(userRepository) as T
         }

         modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
            LoginViewModel(userRepository) as T
         }

         modelClass.isAssignableFrom(MainViewModel::class.java) -> {
            MainViewModel(userRepository) as T
         }

         modelClass.isAssignableFrom(FormWelcomeViewModel::class.java) -> {
            FormWelcomeViewModel(userRepository) as T
         }

         modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
            ProfileViewModel(userRepository) as T
         }

         modelClass.isAssignableFrom(HasilLatihanViewModel::class.java) -> {
            HasilLatihanViewModel(userRepository) as T
         }

         modelClass.isAssignableFrom(EditProfileViewModel::class.java) -> {
            EditProfileViewModel(userRepository) as T
         }

         else -> throw IllegalArgumentException("Unknown ViewModel class " + modelClass.name)
      }
   }

   companion object {
      @JvmStatic
      fun getInstance(context: Context): ViewModelFactory {
         return ViewModelFactory(Injection.provideUserRepository(context))
      }
   }
}