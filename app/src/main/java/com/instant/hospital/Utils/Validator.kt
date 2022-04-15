package com.instant.hospital.Utils

import android.widget.EditText


    fun EditText.validateIsEmpty(text: String): Boolean{
        if (text.isEmpty()){
            return true
        }
        return false
    }


    fun EditText.validatePhoneNumberLength(text: String): Boolean {
        if (text.length == 11){
            return true
        }
        return false
    }


    

