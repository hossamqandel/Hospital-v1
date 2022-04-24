package com.instant.hospital.Ui.Fragments.Receptionist.Calls

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
class RecepCallsViewModel @Inject constructor(private val webServices: WebServices): ViewModel() {

    private val _callsLiveData = SingleLiveEvent<NetworkState>()
    val callsLiveData get() = _callsLiveData

    fun getCallsByDate(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val objectDataReceiver = webServices.getAllCallsByDate(date)
                if (objectDataReceiver.status == Const.BACKEND_STATUS_CODE_SUCCESS){
                    callsLiveData.postValue(NetworkState.getLoaded(objectDataReceiver))
                }
                else {
                    callsLiveData.postValue(NetworkState.getErrorMessage(objectDataReceiver.message))
                }
            }catch (e: Exception){
                callsLiveData.postValue(NetworkState.getExeptionErrorMessage(e))
            }
        }

    }

}