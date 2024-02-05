package com.mlbench.fitmeapp.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mlbench.fitmeapp.R
import com.mlbench.fitmeapp.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachViewMode() {

    }
}