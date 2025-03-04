package com.example.fitsync.data.api

import com.example.fitsync.data.model.EditProfileRequest
import com.example.fitsync.data.model.LatihanRequest
import com.example.fitsync.data.response.GetBMResponse
import com.example.fitsync.data.response.LoginResponse
import com.example.fitsync.data.response.MessageResponse
import com.example.fitsync.data.response.ResponseRiwayatLatihan
import com.example.fitsync.data.response.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

   @FormUrlEncoded
   @POST("login")
   suspend fun login(
      @Field("username") username: String,
      @Field("password") password: String
   ): LoginResponse

   @FormUrlEncoded
   @POST("register")
   suspend fun register(
      @Field("firstName") firstName: String,
      @Field("lastName") lastName: String,
      @Field("username") username: String,
      @Field("password") password: String
   ): MessageResponse

   @GET("dashboard/{userId}")
   suspend fun getDashboardData(
      @Path("userId") userId: String
   ): ResponseRiwayatLatihan

   @GET("seeMetrikBody/{userId}")
   suspend fun getBodyMetric(
      @Path("userId") userId: String
   ): GetBMResponse

   @FormUrlEncoded
   @POST("metrikBody/{userId}")
   suspend fun postBodyMetric(
      @Path("userId") userId: String,
      @Field("weight") weight: Int,
      @Field("height") height: Int
   ): MessageResponse

   @GET("profile/{userId}")
   suspend fun getUserProfile(
      @Path("userId") userId: String
   ): UserProfileResponse

   @POST("completeMovement/{userId}")
   suspend fun addCompleteMovement(
      @Path("userId") userId: String,
      @Body latihanRequest: LatihanRequest
   ): MessageResponse

   @PUT("updateProfile/{userId}")
   suspend fun editProfile(
      @Path("userId") userId: String,
      @Body latihanRequest: EditProfileRequest
   ): MessageResponse
}