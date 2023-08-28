package com.shubhasai.branchinc.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.shubhasai.branchinc.LoginActivity
import com.shubhasai.branchinc.data.UserData
import com.shubhasai.branchinc.databinding.FragmentReplyBinding
import com.shubhasai.branchinc.viewModels.ReplyCallback
import com.shubhasai.branchinc.viewModels.ReplyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReplyFragment : Fragment() {
    private lateinit var binding: FragmentReplyBinding
    private lateinit var viewModel:ReplyViewModel
    private val arg:ReplyFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReplyBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ReplyViewModel::class.java)
        binding.tvThreadId.text = "Thread Id: "+arg.threadId.toString()
        binding.tvThreadBody.text ="Message: "+ arg.body
        binding.tvThreadSender.text = "Sender Id: "+arg.userid
        binding.btnReply.setOnClickListener {
            checkUserToken()
        }
        return binding.root
    }
    fun sendReply(userToken:String){
        val message = binding.etmessage.text.toString()
        val threadId = arg.threadId
        viewModel.Sendreply(message,threadId,userToken,object : ReplyCallback{
            override fun onreplySuccess(response: String) {
                Toast.makeText(activity, "Your Message Sent Successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onreplyError(errorMessage: String) {
                Toast.makeText(activity, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun checkUserToken() {
        GlobalScope.launch(Dispatchers.Main) {
            activity?.let {
                UserData(it).getAccessToken.collect { userToken ->
                    if (userToken.isNotEmpty()) {
                        sendReply(userToken)
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