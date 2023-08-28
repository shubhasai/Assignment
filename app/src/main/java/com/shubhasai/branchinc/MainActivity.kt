package com.shubhasai.branchinc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shubhasai.branchinc.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvGreetings.text =  getGreeting(getTimeOfDay())
    }
    fun getTimeOfDay(): String {
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTimeString = dateFormat.format(currentTime)
        val currentTimeValue = dateFormat.parse(currentTimeString)
        val morningTime = dateFormat.parse("00:00")
        val afternoonTime = dateFormat.parse("12:00")
        val nightTime = dateFormat.parse("18:00")

        return when (currentTimeValue) {
            in morningTime..afternoonTime -> "morning"
            in afternoonTime..nightTime -> "afternoon"
            else -> "Evening"
        }
    }
    fun getGreeting(timeOfDay: String): String {
        return "Good $timeOfDay !"
    }
}