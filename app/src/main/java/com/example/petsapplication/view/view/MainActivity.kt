package com.example.petsapplication.view.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.petsapplication.R


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
    }
}