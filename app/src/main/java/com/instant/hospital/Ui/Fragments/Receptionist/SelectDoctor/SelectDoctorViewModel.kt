package com.instant.hospital.Ui.Fragments.Receptionist.SelectDoctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectDoctorViewModel @Inject constructor(private val webServices: WebServices) :
    ViewModel() {

    private val _loadDoctorsLiveData = SingleLiveEvent<NetworkState>()
    val loadDoctorsLiveData get() = _loadDoctorsLiveData

    fun getAllDoctors() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val objectDataReceiver = webServices.getAllUsers("doctor")

                if (objectDataReceiver.status == Const.BACKEND_STATUS_CODE_SUCCESS) {
                    _loadDoctorsLiveData.postValue(NetworkState.getLoaded(objectDataReceiver))
                }
                else {
                    _loadDoctorsLiveData.postValue(NetworkState.getErrorMessage(objectDataReceiver.message))
                }
            } catch (ex: Exception) {
                _loadDoctorsLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }
        }

    }
}