package com.shubhasai.branchinc.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubhasai.branchinc.LoginActivity
import com.shubhasai.branchinc.MainActivity
import com.shubhasai.branchinc.data.UserData
import com.shubhasai.branchinc.data.messages
import com.shubhasai.branchinc.data.messagesItem
import com.shubhasai.branchinc.databinding.FragmentThreadsBinding
import com.shubhasai.branchinc.viewModels.FetchCallback
import com.shubhasai.branchinc.viewModels.ThreadsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ThreadsFragment : Fragment(),ThreadsAdapter.ThreadClicked {
    private lateinit var binding: FragmentThreadsBinding
    private lateinit var ViewModel:ThreadsViewModel
    private lateinit var adapter:ThreadsAdapter
    private val messages:ArrayList<messagesItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThreadsBinding.inflate(layoutInflater)
        // Inside onCreateView
        ViewModel = ViewModelProvider(this).get(ThreadsViewModel::class.java)
        binding.rvThreads.layoutManager = LinearLayoutManager(activity)
        adapter = ThreadsAdapter(activity,messages,this)
        binding.rvThreads.adapter = adapter
        checkUserToken()
        return binding.root
    }
    fun fetchThreads(token:String){
        ViewModel.getThreads(token,object: FetchCallback {
            override fun onfetchSuccess(response: messages) {
                messages.clear()
                messages.addAll(response)
                adapter.notifyDataSetChanged()
            }

            override fun onfetchError(errorMessage: String) {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onThreadClicked(thread: messagesItem) {
        val action = ThreadsFragmentDirections.actionThreadsFragmentToReplyFragment(thread.thread_id,thread.body,thread.user_id)
        findNavController().navigate(action)
    }
    private fun checkUserToken() {
        GlobalScope.launch(Dispatchers.Main) {
            activity?.let {
                UserData(it).getAccessToken.collect { userToken ->
                    if (userToken.isNotEmpty()) {
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