package com.shubhasai.branchinc.networks
import com.shubhasai.branchinc.data.replyMessage
import com.shubhasai.branchinc.utils.UrlDetails
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReplyRepository {
    private val apiService: ReplyApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(UrlDetails.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ReplyApiService::class.java)
    }
    suspend fun ReplyThread(token:String,request:replyMessage) = apiService.reply(token,request)
}