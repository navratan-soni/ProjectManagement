package com.pmgmt.mainapp.data.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun provideApiService(context: Context): ApiService {

        val client = OkHttpClient.Builder()
            .addInterceptor(MockInterceptor(context))
            //.addNetworkInterceptor(MockInterceptor(context))// Add mock interceptor
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyurl.com/") // Placeholder base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}