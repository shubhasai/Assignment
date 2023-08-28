package com.shubhasai.branchinc.networks

import com.shubhasai.branchinc.data.AuthRequest
import com.shubhasai.branchinc.utils.UrlDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThreadRepository {
    private val apiService: ThreadsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(UrlDetails.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ThreadsApiService::class.java)
    }
    suspend fun getThreads(token:String) = apiService.getThread(token)
}