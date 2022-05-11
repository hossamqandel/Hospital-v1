package com.instant.hospital.Ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.instant.hospital.R
import com.instant.hospital.Utils.changeStatusBarColor
import com.instant.hospital.Utils.setTransparentStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        setTransparentStatusBar()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


//        changeStatusBarColor(
//            ContextCompat.getColor(
//                baseContext,
//                R.color.white
//            ), true
//        )
    }




}