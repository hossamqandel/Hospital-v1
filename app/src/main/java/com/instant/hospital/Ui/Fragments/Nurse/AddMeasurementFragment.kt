package com.instant.hospital.Ui.Fragments.Nurse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelCaseDetails
import com.instant.hospital.R
import com.instant.hospital.Utils.showToastShort
import com.instant.hospital.databinding.FragmentAddMeasurementBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddMeasurementFragment : Fragment() {

    //TODO Test it when endPoints Fixed
    private var _binding: FragmentAddMeasurementBinding? = null
    private val binding get() = _binding!!
    private val mMeasurementViewModel: MeasurementViewModel by viewModels()
    private var mCallId: Int? = null
    private var mStatus: String? = null
    private var bloodPressure: String? = null
    private var sugarAnalysis: String? = null
    val cal = Calendar.getInstance()

    var note = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddMeasurementBinding.inflate(inflater, container, false)
        val view = binding.root
        mCallId = AddMeasurementFragmentArgs.fromBundle(requireArguments()).callId
        mStatus = AddMeasurementFragmentArgs.fromBundle(requireArguments()).status
        
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAndShowCurrentDate()
        loadCachedUserData()
        onClicks()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicks(){
        binding.apply {
            nurseMeasurementAddBTN.setOnClickListener { validation() }
        }
    }

    private fun validation() {
        binding.apply {
            bloodPressure = nurseMeasurementBloodPressureET.text.toString().trim()
            sugarAnalysis = nurseMeasurementSugarAnalysisET.text.toString().trim()
            note = nurseMeasurementAddNoteET.text.toString().trim()

            if (bloodPressure.equals("")){ nurseMeasurementBloodPressureET.error = getString(R.string.required) }
            if (sugarAnalysis.equals("")){ nurseMeasurementSugarAnalysisET.error = getString(R.string.required) }
            else {
                mMeasurementViewModel.addMeasurement(mCallId!!, bloodPressure.toString(), sugarAnalysis.toString(), note, mStatus.toString())
            }

        }
    }

    private fun observe(){
        mMeasurementViewModel.measurementLiveData.observe(this, {
            showToastShort(it.status)
            when(it.status){
                NetworkState.Status.RUNNING -> {}
                NetworkState.Status.SUCCESS -> {
                    val modelData = it.data as ModelCaseDetails
                    clearInputs()
                }
                NetworkState.Status.FAILED -> {}
            }
        })
    }

    private fun loadCachedUserData(){
        binding.apply {
//            nurseMeasurementUserImgIV. TODO set User profile image in the future
            nurseMeasurementNameTV.text = MySharedPref.getUserName()
            nurseMeasurementUserSpecialistTV.text = "Specialist - ${MySharedPref.getUserType()}"
        }
    }

    private fun clearInputs(){
        binding.apply {
            nurseMeasurementAddNoteET.text.clear()
            nurseMeasurementBloodPressureET.text.clear()
            nurseMeasurementSugarAnalysisET.text.clear()
        }
    }

    private fun getAndShowCurrentDate(){
        val myFormat = "dd MMM yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        val mDate = sdf.format(cal.time)
        binding.nurseMeasurementDateTV.text = mDate.toString()
    }
}