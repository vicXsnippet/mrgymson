package com.example.fitsync.data.api

import com.example.fitsync.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {

        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .build()
            chain.proceed(requestHeaders)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BaseUrlIdentification)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}