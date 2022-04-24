package com.instant.hospital.Ui.Fragments.Receptionist.CaseDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CaseDetailsViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    private val _caseDetailsLiveData = SingleLiveEvent<NetworkState>()
    val caseDetailsLiveData get() = _caseDetailsLiveData

// (You should receive caseId from all cases Fragment from RecyclerView when Onclick(Int) )
    fun caseDetailsReceiver(caseId: Int = 10) { //this '10' is temporary

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val objectDataReceiver = webServices.getSingleCaseDetailsByCaseId(caseId)

                if (objectDataReceiver.status == Const.BACKEND_STATUS_CODE_SUCCESS) {
                    _caseDetailsLiveData.postValue(NetworkState.getLoaded(objectDataReceiver))
                } else {
                    _caseDetailsLiveData.postValue(NetworkState.getErrorMessage(objectDataReceiver.message))
                }
            } catch (ex: Exception) {
                _caseDetailsLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }
        }
    }
}