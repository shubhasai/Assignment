package com.shubhasai.branchinc.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhasai.branchinc.data.replyMessage
import com.shubhasai.branchinc.networks.ReplyRepository
import kotlinx.coroutines.launch

class ReplyViewModel:ViewModel() {
    private val ReplyRepository = ReplyRepository()
    fun Sendreply(reply:String,threadId:Int,token:String,replyCallBack: ReplyCallback){
        val request = replyMessage(threadId,reply)
        viewModelScope.launch {
            try {
                val replyResponse = ReplyRepository.ReplyThread(token,request)
                replyCallBack.onreplySuccess("Reply Sent")
            } catch (e: Exception) {
                // Handle exception based on your requirements
                replyCallBack.onreplyError("Something Went Wrong: ${e.message}")
            }
        }
    }
}
interface ReplyCallback {
    fun onreplySuccess(response: String)
    fun onreplyError(errorMessage: String)
}