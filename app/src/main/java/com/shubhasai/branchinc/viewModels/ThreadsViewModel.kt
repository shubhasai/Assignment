package com.shubhasai.branchinc.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhasai.branchinc.data.messages
import com.shubhasai.branchinc.networks.ThreadRepository
import kotlinx.coroutines.launch

class ThreadsViewModel():ViewModel() {
    private val threadRepository = ThreadRepository()
    fun getThreads(token:String,FetchCallBack: FetchCallback) {

        viewModelScope.launch{
            try {
                val threadResponse = threadRepository.getThreads(token)
                FetchCallBack.onfetchSuccess(threadResponse)
            } catch (e: Exception) {
                // Handle exception based on your requirements
                FetchCallBack.onfetchError("Something Went Wrong: ${e.message}")
            }
        }
    }
}
interface FetchCallback {
    fun onfetchSuccess(response: messages)
    fun onfetchError(errorMessage: String)
}