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

@HiltViewModel
class DoctorCaseViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {


    private val _doctorCaseLiveData = SingleLiveEvent<NetworkState>()
    val doctorCaseLiveData get() = _doctorCaseLiveData

    fun getCaseDetailsById(caseId: Int = 7){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val dataReceiverObject = webServices.getSingleCaseDetailsByCaseId(caseId)

                    if (dataReceiverObject.status == Const.BACKEND_STATUS_CODE_SUCCESS){
                        _doctorCaseLiveData.postValue(NetworkState.getLoaded(dataReceiverObject))
                }
                else {
                        _doctorCaseLiveData.postValue(NetworkState.getErrorMessage(dataReceiverObject.message))
                    }
            }catch (ex: Exception){
                _doctorCaseLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }
        }
    }

}