package com.shubhasai.branchinc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.shubhasai.branchinc.data.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            checkUserToken()
        }, 4000)
    }
    private fun checkUserToken() {
        GlobalScope.launch(Dispatchers.Main) {
            UserData(this@SplashActivity).getAccessToken.collect { userToken ->
                if (userToken.isNotEmpty()) {
                    // User token exists, navigate to the main activity
                    Toast.makeText(this@SplashActivity, "Regaining Session", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // User token doesn't exist, navigate to the login activity
                    Toast.makeText(this@SplashActivity, "Login Again", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        }
    }
}