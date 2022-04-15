package com.instant.hospital.Data.Local

import android.content.Context
import android.content.SharedPreferences


object MySharedPref {

    private var mAppContext: Context? = null
    private val SHARED_PREFERENCES_NAME = "user data"
    private const val USER_PHONE = "mobile"
    private const val TYPE = "type"
    private const val USER_NAME = "user name"
    private const val USER_EMAIL = "user email"
    private const val USER_TOKEN = "token"
    private const val USER_ID = "user id"
    private const val USER_ATTENDED = "attended"
    private const val USER_LEAVING = "leaving"

    fun init(context: Context?) {
        mAppContext = context
    }

    private fun getSharedPreferences(): SharedPreferences? {
        return mAppContext?.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    /**********************************/

    fun setUserId(container: Int) {
        val editor = getSharedPreferences()?.edit()
        editor?.putInt(USER_ID, container)?.apply()
    }

    fun getUserId(): Int = getSharedPreferences()?.getInt(USER_ID, 0)!!

    /**********************************/


    fun setUserEmail(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_EMAIL, container)?.apply()
    }

    fun getUserEmail(): String = getSharedPreferences()?.getString(USER_EMAIL, "")!!

    /**********************************/

    fun setUserName(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_NAME, container)?.apply()
    }

    fun getUserName(): String = getSharedPreferences()?.getString(USER_NAME, "")!!

    /**********************************/

    fun setUserPhone(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_PHONE, container)?.apply()
    }

    fun getUserPhone(): String = getSharedPreferences()?.getString(USER_PHONE, "")!!

    /**********************************/

    fun setUserSecretToken(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_TOKEN, container)?.apply()
    }

    fun getUserSecretToken(): String = getSharedPreferences()?.getString(USER_TOKEN, "")!!

    /**********************************/

    fun setUserType(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(TYPE, container)?.apply()
    }

    fun getUserType(): String = getSharedPreferences()?.getString(TYPE, "")!!

    /**********************************/

    fun setUserAttended(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_ATTENDED, container)?.apply()
    }

    fun getUserAttended(): String = getSharedPreferences()?.getString(USER_ATTENDED, "")!!

    /**********************************/

    fun setUserLeaving(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_LEAVING, container)?.apply()
    }

    fun getUserLeaving(): String = getSharedPreferences()?.getString(USER_LEAVING, "")!!

    /**********************************/

    fun clearData() {
        getSharedPreferences()?.edit()?.clear()?.apply()
    }


}































