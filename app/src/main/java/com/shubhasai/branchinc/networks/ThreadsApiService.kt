package com.shubhasai.branchinc.networks

import com.shubhasai.branchinc.data.AuthRequest
import com.shubhasai.branchinc.data.authresponse
import com.shubhasai.branchinc.data.messages
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ThreadsApiService {
    @GET("api/messages")
    suspend fun getThread(@Header("X-Branch-Auth-Token") token: String): messages
}
