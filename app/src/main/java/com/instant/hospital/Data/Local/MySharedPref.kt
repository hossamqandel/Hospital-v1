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
    private const val USER_STATUS = "status"
    private const val USER_GENDER = "gender"
    private const val USER_BIRTH_DATE = "birth date"
    private const val USER_ADDRESS = "address"


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

    fun getUserSecretToken(): String{
        return getSharedPreferences()?.getString(USER_TOKEN, "")!!
    }

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
    fun setUserStatus(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_STATUS, container)?.apply()
    }

    fun getUserStatus(): String = getSharedPreferences()?.getString(USER_STATUS, "")!!

    /**********************************/

    fun setUserGender(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_GENDER, container)?.apply()
    }

    fun getUserGender(): String = getSharedPreferences()?.getString(USER_GENDER, "")!!
    /**********************************/

    fun setUserBirthDate(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_BIRTH_DATE, container)?.apply()
    }

    fun getUserBirthDate(): String = getSharedPreferences()?.getString(USER_BIRTH_DATE, "")!!

    /**********************************/

    fun setUserAddress(container: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(USER_ADDRESS, container)?.apply()
    }

    fun getUserAddress(): String = getSharedPreferences()?.getString(USER_ADDRESS, "")!!


    /**********************************/

//    fun setUserStatus(container: String) {
//        val editor = getSharedPreferences()?.edit()
//        editor?.putString(USER_ADDRESS, container)?.apply()
//    }
//
//    fun getUserStatus(): String = getSharedPreferences()?.getString(USER_ADDRESS, "")!!


    /**********************************/

    fun clearData() {
        getSharedPreferences()?.edit()?.clear()?.apply()
    }



}