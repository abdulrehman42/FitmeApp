package com.mlbench.fitmeapp.view.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mlbench.fitmeapp.R
import com.mlbench.fitmeapp.databinding.ActivityAuthBinding
import com.mlbench.fitmeapp.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        MainScope().launch {
            delay(2000)
            startActivity(Intent(this@SplashScreen,AuthActivity::class.java))
        }
    }
}