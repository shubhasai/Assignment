package com.shubhasai.branchinc.networks

import com.shubhasai.branchinc.data.AuthRequest
import com.shubhasai.branchinc.data.authresponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/login")
    suspend fun getAuth(@Body request: AuthRequest): authresponse
}
