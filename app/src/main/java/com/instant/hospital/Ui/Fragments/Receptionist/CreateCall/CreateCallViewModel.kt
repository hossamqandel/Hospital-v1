package com.instant.hospital.Ui.Fragments.Receptionist.CreateCall

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
class CreateCallViewModel @Inject constructor(private val webServices: WebServices) : ViewModel(){

    private val _creatCallLiveData = SingleLiveEvent<NetworkState>()
    val creatCallLiveData get() = _creatCallLiveData


    fun PostCall(patient_name: String, doctor_id: Int, age: String, phone: String, description: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val objectDataPoster = webServices.createCall(patient_name, doctor_id, age, phone, description)

                if (objectDataPoster.status == Const.BACKEND_STATUS_CODE_SUCCESS){
                    _creatCallLiveData.postValue(NetworkState.getLoaded(objectDataPoster))
                } else {
                    _creatCallLiveData.postValue(NetworkState.getErrorMessage(objectDataPoster.message))
                }
            }catch (ex: Exception){
                _creatCallLiveData.postValue(NetworkState.getExeptionErrorMessage(ex))
            }

        }
    }

}