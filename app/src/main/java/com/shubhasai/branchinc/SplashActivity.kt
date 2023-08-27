package com.shubhasai.branchinc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // Start your main activity after the delay
//            if (curuser==null){
//
//            }
//            else{
//                Userinfo.userid = curuser.uid
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 4000)
    }
}