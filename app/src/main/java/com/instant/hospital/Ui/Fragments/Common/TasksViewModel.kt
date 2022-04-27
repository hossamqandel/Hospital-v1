package com.instant.hospital.Ui.Fragments.Common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Data.Network.SingleLiveEvent
import com.instant.hospital.Data.Network.WebServices
import com.instant.hospital.Models.ModelAllTasks
import com.instant.hospital.Utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val webServices: WebServices) : ViewModel() {
    private val _tasksLiveData = SingleLiveEvent<NetworkState>()
    val tasksLiveData get() = _tasksLiveData

    fun getAllCallsByDate(date: String) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val dataObjReceiver = webServices.getAllCallsByDate(date)

                if (dataObjReceiver.status == Const.BACKEND_STATUS_CODE_SUCCESS){
                    _tasksLiveData.postValue(NetworkState.getLoaded(dataObjReceiver))
                } else {
                    _tasksLiveData.postValue(NetworkState.getErrorMessage(dataObjReceiver.message))
                }
            } catch (ex: Exception) {
                _tasksLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }
        }

    }
}