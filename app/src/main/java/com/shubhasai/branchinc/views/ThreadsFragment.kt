package com.shubhasai.branchinc.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shubhasai.branchinc.databinding.FragmentThreadsBinding

class ThreadsFragment : Fragment() {
    private lateinit var binding: FragmentThreadsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThreadsBinding.inflate(layoutInflater)
        return binding.root
    }

}