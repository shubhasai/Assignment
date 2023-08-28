package com.shubhasai.branchinc

import AuthCallback
import AuthViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shubhasai.branchinc.data.UserData
import com.shubhasai.branchinc.data.authresponse
import com.shubhasai.branchinc.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflate the layout
        setContentView(binding.root) // Set the content view
        authViewModel = AuthViewModel()
        binding.btnLogin.setOnClickListener {
            AuthenticateUser()
        }
    }
    fun AuthenticateUser(){
        val email = binding.etemail.text.toString()
        val password = binding.etPass.text.toString()
        authViewModel.authenticateUser(email, password, object : AuthCallback {
            override fun onAuthSuccess(response: authresponse) {
                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    UserData(this@LoginActivity).saveToken(response.auth_token)
                }
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
            override fun onAuthError(errorMessage: String) {
                // Handle error message based on your requirements
                Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

