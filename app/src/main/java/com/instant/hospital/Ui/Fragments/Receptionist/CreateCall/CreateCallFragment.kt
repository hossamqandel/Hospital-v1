package com.instant.hospital.Ui.Fragments.Receptionist.CreateCall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.R
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.Utils.showToastShort
import com.instant.hospital.databinding.FragmentCreateCallBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCallFragment : Fragment() {

    //TODO if user wrote some inputs and clicked on select doctor .. when he back again to create call all inputs cleared

    private var _binding: FragmentCreateCallBinding? = null
    private val binding get() = _binding!!
    private val mCreateCallViewModel:CreateCallViewModel by viewModels()
    private var doctorName: String? = null
    private var doctorId: Int? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCreateCallBinding.inflate(inflater, container, false)
        val view = binding.root
        doctorName = CreateCallFragmentArgs.fromBundle(arguments!!).doctorName
        doctorId = CreateCallFragmentArgs.fromBundle(arguments!!).doctorId
        binding.createCallSelectDoctorTV.text = doctorName
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicks(){
        binding.apply {
            createCallBackArrowIV.setOnClickListener { createCallBackArrowIV.onBackPressed() }
            createCallSelectDoctorTV.setOnClickListener { setNavigation(R.id.action_createCallFragment_to_selectDoctorFragment) }
            createCallSendCallBTN.setOnClickListener {
                binding.apply {
                    val patientName = createCallPatientNameET.text.toString().trim()
                    val patientAge = createCallPatientAgeET.text.toString().trim()
                    val patientPhoneNum = createCallPatientPhonNumET.text.toString().trim()
                    val doctorName = createCallSelectDoctorTV.text.toString().trim()
                    val caseDescription = createCallCaseDescriptionET.text.toString().trim()

                    when {
                        patientName.isEmpty() -> { createCallPatientNameET.error = getString(R.string.required)}
                        patientName.length < 2 -> { createCallPatientNameET.error = getString(R.string.name_length_at_least_two) }
                        patientAge.isEmpty() -> { createCallPatientAgeET.error = getString(R.string.required) }
                        patientPhoneNum.isEmpty() -> { createCallPatientPhonNumET.error = getString(R.string.required) }
                        patientPhoneNum.length != 11 -> { createCallPatientPhonNumET.error = getString(R.string.phone_length) }
                        doctorName.isEmpty() -> { createCallPatientPhonNumET.error = getString(R.string.required) }
                        caseDescription.isEmpty() -> { createCallPatientPhonNumET.error = getString(R.string.required) }
                        else -> { postCallRequestStatus(patientName, doctorId, patientAge, patientPhoneNum, caseDescription) }
                    }
                }
            }
        }
    }

    private fun postCallRequestStatus(patient_name: String, doctor_id: Int?, age: String, phone: String, description: String){
        mCreateCallViewModel.PostCall(patient_name, doctor_id!!, age, phone, description)
        mCreateCallViewModel.creatCallLiveData.observe(this, {
            when(it.status){

                NetworkState.Status.RUNNING -> {}
                NetworkState.Status.SUCCESS -> {
                    showToastShort("Success")
                }
                NetworkState.Status.FAILED -> {}
            }
        })
    }

}