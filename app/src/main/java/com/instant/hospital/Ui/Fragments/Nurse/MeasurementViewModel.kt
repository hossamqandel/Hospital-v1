package com.instant.hospital.Ui.Fragments.Nurse

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
class MeasurementViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    private val _measurementLiveData = SingleLiveEvent<NetworkState>()
    val measurementLiveData get() = _measurementLiveData

    fun addMeasurement(
        callId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        note: String,
        status: String
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val objectDataPoster = webServices.addMeasurement(callId, bloodPressure, sugarAnalysis, note, status)

                if (objectDataPoster.status == Const.BACKEND_STATUS_CODE_SUCCESS) {
                    _measurementLiveData.postValue(NetworkState.getLoaded(objectDataPoster))
                } else {
                    _measurementLiveData.postValue(NetworkState.getErrorMessage(objectDataPoster.message))
                }
            } catch (ex: Exception) {
                _measurementLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }
        }
    }
}