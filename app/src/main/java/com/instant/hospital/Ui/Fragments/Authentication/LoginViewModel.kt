package com.instant.hospital.Ui.Fragments.Authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Models.Data
import com.instant.hospital.Models.ModelUser
import com.instant.hospital.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.http.Field
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val webServices: WebServices): ViewModel() {

}