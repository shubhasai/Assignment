package com.shubhasai.branchinc.networks

import com.shubhasai.branchinc.data.AuthRequest
import com.shubhasai.branchinc.utils.UrlDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepository {
    private val apiService: AuthApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(UrlDetails.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(AuthApiService::class.java)
    }
    suspend fun getAuth(email: String, password: String) = apiService.getAuth(AuthRequest(email, password))
}