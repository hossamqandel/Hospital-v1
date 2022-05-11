package com.instant.hospital.Ui.Fragments.Authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Utils.Const.BACKEND_STATUS_CODE_SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    private var _loginLiveData = SingleLiveEvent<NetworkState>()
    val loginLiveData get() = _loginLiveData

    fun loginOrder(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val objectDataPoster = webServices.login(email, password)
                if (objectDataPoster.status == BACKEND_STATUS_CODE_SUCCESS) {
                    _loginLiveData.postValue(NetworkState.getLoaded(objectDataPoster))
                } else {
                    _loginLiveData.postValue(NetworkState.getErrorMessage(objectDataPoster.message))
                }
            } catch (e: Exception) {
                _loginLiveData.postValue(NetworkState.getExeptionErrorMessage(e))
            }
        }
    }

}