package com.instant.hospital.Ui.Fragments.HR.EmployeeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Models.ModelAllUsers
import com.instant.hospital.Utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {

    private val _employeeListLiveData = SingleLiveEvent<NetworkState>()
    val employeeListLiveData get() = _employeeListLiveData

    fun getAllCalls(type: String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val objectDataReceiver: ModelAllUsers = webServices.getAllUsers(type)
                if (objectDataReceiver.status == Const.BACKEND_STATUS_CODE_SUCCESS){
                    _employeeListLiveData.postValue(NetworkState.getLoaded(objectDataReceiver))
                }
                else {
                    _employeeListLiveData.postValue(NetworkState.getErrorMessage(objectDataReceiver.message))
                }

            }catch (e: Exception){
                _employeeListLiveData.postValue(NetworkState.getExeptionErrorMessage(e))
            }
            val call: ModelAllUsers = webServices.getAllUsers(type)
            _employeeListLiveData.postValue(NetworkState.getLoaded(call))
        }

    }
}