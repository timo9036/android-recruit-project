package com.hahow.android_recruit_project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import `in`.hahow.android_recruit_project.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.courseFragment)
    }
}