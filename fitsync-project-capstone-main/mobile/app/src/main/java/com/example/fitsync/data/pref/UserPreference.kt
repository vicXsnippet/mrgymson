package com.example.fitsync.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.fitsync.data.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreference(private val dataStore: DataStore<Preferences>) {

   suspend fun saveSession(user: LoginResponse) {
      user.userId?.let { userId ->
         dataStore.edit { preferences ->
            preferences[NAME_KEY] = userId
         }
      }
   }

   fun getSession(): Flow<String> {
      return dataStore.data.map { preferences ->
         preferences[NAME_KEY] ?: ""
      }
   }
   suspend fun logout() {
      dataStore.edit { preferences ->
         preferences.clear()
      }
   }

   companion object {
      @Volatile
      private var INSTANCE: UserPreference? = null

      private val NAME_KEY = stringPreferencesKey("userId")
      fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
         return INSTANCE ?: synchronized(this) {
            val instance = UserPreference(dataStore)
            INSTANCE = instance
            instance
         }
      }
   }
}