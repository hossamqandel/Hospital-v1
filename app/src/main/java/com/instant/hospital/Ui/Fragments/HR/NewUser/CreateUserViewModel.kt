package com.instant.hospital.Ui.Fragments.HR.NewUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(private val webServices: WebServices): ViewModel() {

    private val _registerLiveData = SingleLiveEvent<NetworkState>()
    val registerLiveData get() = _registerLiveData

    fun registrationOrder(
        email: String,
        password: String,
        first_name: String,
        last_name: String,
        gender: String,
        specialist: String,
        birthday: String,
        status: String,
        address: String,
        mobile: String,
        type: String, ) {

        _registerLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val objectDataPoster = webServices.createUser(email, password, first_name, last_name, gender, specialist, birthday, status, address, mobile, type)
                if (objectDataPoster.status == Const.BACKEND_STATUS_CODE_SUCCESS){
                    withContext(Dispatchers.Main){
                        _registerLiveData.value = NetworkState.getLoaded(objectDataPoster)
                    }
                }

                else{
                    _registerLiveData.postValue(NetworkState.getErrorMessage(objectDataPoster.message))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _registerLiveData.postValue(NetworkState.getExeptionErrorMessage(e))
            }
        }
    }
}