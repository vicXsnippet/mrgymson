package com.example.fitsync.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.fitsync.data.ResultState
import com.example.fitsync.data.api.ApiService
import com.example.fitsync.data.model.EditProfileRequest
import com.example.fitsync.data.model.LatihanRequest
import com.example.fitsync.data.pref.UserPreference
import com.example.fitsync.data.response.GetBMResponse
import com.example.fitsync.data.response.LoginResponse
import com.example.fitsync.data.response.MessageResponse
import com.example.fitsync.data.response.ResponseRiwayatLatihan
import com.example.fitsync.data.response.UserProfileResponse
import com.example.fitsync.utils.extractErrorMessage
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class UserRepository private constructor(
   private val apiService: ApiService,
   private val userPreference: UserPreference
) {

   suspend fun saveSession(user: LoginResponse) {
      userPreference.saveSession(user)
   }

   fun getSession(): Flow<String> {
      return userPreference.getSession()
   }

   suspend fun logout() {
      userPreference.logout()
   }

   fun register(
      firstName: String,
      lastName: String,
      username: String,
      password: String
   ): LiveData<ResultState<MessageResponse>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.register(firstName, lastName, username, password)
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("message", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("An unknown error occurred"))
            }
         }
      }
   }

   fun login(username: String, password: String): LiveData<ResultState<LoginResponse>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.login(username, password)
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("error", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("inikah salah?"))
            }
         }
      }
   }

   fun getDashboardData(id: String): LiveData<ResultState<ResponseRiwayatLatihan>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.getDashboardData(id)
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("error", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("Terjadi Kesalahan"))
            }
         }
      }
   }

   fun getBodyMetrik(userId: String): LiveData<ResultState<GetBMResponse>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.getBodyMetric(userId)
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("error", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("Terjadi Kesalahan"))
            }
         }
      }
   }

   fun postBobyMetrik(
      userId: String,
      weight: Int,
      height: Int
   ): LiveData<ResultState<MessageResponse>> =
      liveData {
         emit(ResultState.Loading)
         try {
            val successResponse = apiService.postBodyMetric(userId, weight, height)
            emit(ResultState.Success(successResponse))
         } catch (e: Exception) {
            when (e) {
               is HttpException -> {
                  val errorBody = e.response()?.errorBody()?.string()
                  val errorMessage = extractErrorMessage("error", errorBody)
                  emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
               }

               is IOException -> {
                  emit(ResultState.Error("Network error. Please check your connection and try again."))
               }

               else -> {
                  emit(ResultState.Error("Terjadi Kesalahan"))
               }
            }
         }

      }

   fun getUserProfile(userId: String): LiveData<ResultState<UserProfileResponse>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.getUserProfile(userId)
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("error", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("Terjadi Kesalahan"))
            }
         }
      }
   }

   fun addCompleteMovement(
      userId: String,
      requestBody: LatihanRequest
   ): LiveData<ResultState<MessageResponse>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.addCompleteMovement(
            userId,
            requestBody
         )
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("error", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("Terjadi Kesalahan"))
            }
         }
      }
   }

   fun editProfile(
      userId: String,
      userBody: EditProfileRequest
   ): LiveData<ResultState<MessageResponse>> = liveData {
      emit(ResultState.Loading)
      try {
         val successResponse = apiService.editProfile(
            userId,
            userBody
         )
         emit(ResultState.Success(successResponse))
      } catch (e: Exception) {
         when (e) {
            is HttpException -> {
               val errorBody = e.response()?.errorBody()?.string()
               val errorMessage = extractErrorMessage("error", errorBody)
               emit(ResultState.Error(errorMessage ?: "An unknown error occurred"))
            }

            is IOException -> {
               emit(ResultState.Error("Network error. Please check your connection and try again."))
            }

            else -> {
               emit(ResultState.Error("Terjadi Kesalahan"))
            }
         }
      }

   }

   companion object {
      fun getInstance(
         apiService: ApiService,
         userPreference: UserPreference
      ): UserRepository = UserRepository(apiService, userPreference)
   }
}