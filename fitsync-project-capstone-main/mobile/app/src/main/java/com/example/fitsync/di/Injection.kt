package com.example.fitsync.di

import android.content.Context
import com.example.fitsync.data.api.ApiConfig
import com.example.fitsync.data.pref.UserPreference
import com.example.fitsync.data.pref.dataStore
import com.example.fitsync.data.repository.UserRepository

object Injection {

    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(apiService,pref)
    }
}