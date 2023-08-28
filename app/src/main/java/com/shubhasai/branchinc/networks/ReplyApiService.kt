package com.shubhasai.branchinc.networks
import com.shubhasai.branchinc.data.messages
import com.shubhasai.branchinc.data.messagesItem
import com.shubhasai.branchinc.data.replyMessage
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReplyApiService {
    @POST("api/messages")
    suspend fun reply(@Header("X-Branch-Auth-Token") token: String,@Body request: replyMessage): messagesItem
}