package com.instant.hospital.Application

import android.app.Application
import com.instant.hospital.Data.Local.MySharedPref
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPref.init(this)
    }
}