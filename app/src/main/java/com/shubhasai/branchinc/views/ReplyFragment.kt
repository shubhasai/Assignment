package com.shubhasai.branchinc.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubhasai.branchinc.LoginActivity
import com.shubhasai.branchinc.data.UserData
import com.shubhasai.branchinc.data.messages
import com.shubhasai.branchinc.data.messagesItem
import com.shubhasai.branchinc.databinding.FragmentReplyBinding
import com.shubhasai.branchinc.viewModels.FetchCallback
import com.shubhasai.branchinc.viewModels.ReplyCallback
import com.shubhasai.branchinc.viewModels.ReplyViewModel
import com.shubhasai.branchinc.viewModels.ThreadsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReplyFragment : Fragment() {
    private lateinit var binding: FragmentReplyBinding
    private lateinit var viewModel:ReplyViewModel
    private lateinit var threadViewModel:ThreadsViewModel
    private lateinit var adapter:DetailThreadAdapter
    private val messages:ArrayList<messagesItem> = ArrayList()
    private val arg:ReplyFragmentArgs by navArgs()
    private var token:MutableLiveData<String> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReplyBinding.inflate(layoutInflater)
        threadViewModel = ViewModelProvider(this).get(ThreadsViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ReplyViewModel::class.java)
        binding.tvThreadId.text = "Thread Id: "+arg.threadId.toString()
        binding.tvThreadBody.text ="Message: "+ arg.body
        binding.tvThreadSender.text = "Sender Id: "+arg.userid
        checkUserToken()
        binding.btnReply.setOnClickListener {
            token.value?.let { it1 -> sendReply(it1) }
        }
        binding.rvThreads.layoutManager = LinearLayoutManager(activity)
        adapter = DetailThreadAdapter(activity,messages)
        binding.rvThreads.adapter = adapter
        return binding.root
    }
    fun sendReply(userToken:String){
        val message = binding.etmessage.text.toString()
        val threadId = arg.threadId
        if (message.isEmpty()){
            Toast.makeText(activity, "Please Enter Message", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            viewModel.Sendreply(message,threadId,userToken,object : ReplyCallback{
                override fun onreplySuccess(response: String) {
                    Toast.makeText(activity, "Your Message Sent Successfully", Toast.LENGTH_SHORT).show()
                }
                override fun onreplyError(errorMessage: String) {
                    Toast.makeText(activity, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
    fun fetchThreads(token:String){
        if (token.isEmpty()){
            Toast.makeText(activity, "Login Again", Toast.LENGTH_SHORT).show()
            return
        }
        threadViewModel.getThreads(token,object: FetchCallback {
            override fun onfetchSuccess(response: messages) {
                messages.clear()
                for (message in response){
                    if (message.thread_id == arg.threadId){
                        messages.add(message)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onfetchError(errorMessage: String) {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun checkUserToken() {
        GlobalScope.launch(Dispatchers.Main) {
            activity?.let {
                UserData(it).getAccessToken.collect { userToken ->
                    if (userToken.isNotEmpty()) {
                        token.value = userToken
                        fetchThreads(userToken)
                    }
                    else {
                        // User token doesn't exist, navigate to the login activity
                        Toast.makeText(activity, "Login Again", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}