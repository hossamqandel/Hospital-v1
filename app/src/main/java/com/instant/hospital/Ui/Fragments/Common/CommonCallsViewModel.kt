package com.instant.hospital.Ui.Fragments.Common

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
import kotlin.Exception

@HiltViewModel
class CommonCallsViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    private val _commonCallsLiveData = SingleLiveEvent<NetworkState>()
    val commonCallsLiveData get() = _commonCallsLiveData


    fun getCalls(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dataReceiverObject = webServices.getAllCallsByDate(date)

                if (dataReceiverObject.status == Const.BACKEND_STATUS_CODE_SUCCESS) {
                    _commonCallsLiveData.postValue(NetworkState.getLoaded(dataReceiverObject))
                } else {
                    _commonCallsLiveData.postValue(NetworkState.getErrorMessage(dataReceiverObject.message))
                }
            } catch (ex: Exception) {
                _commonCallsLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }
        }
    }
}