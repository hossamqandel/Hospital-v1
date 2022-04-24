package com.instant.hospital.Ui.Fragments.Receptionist.CaseDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelCaseDetails
import com.instant.hospital.R
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.Utils.showToastShort
import com.instant.hospital.databinding.FragmentCaseDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseDetailsFragment : Fragment() {

    //TODO This fragment jobs almost done.. the only task required for it .. (receive caseId from all cases Fragment from RecyclerView when Onclick(Int) )
    private var _binding: FragmentCaseDetailsBinding? = null
    private val binding get() = _binding!!
    private val mCaseDetailsViewModel: CaseDetailsViewModel by viewModels()
    private var _dataOfCaseDetails: ModelCaseDetails? = null
    private val dataOfCaseDetails get() = _dataOfCaseDetails

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCaseDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        mCaseDetailsViewModel.caseDetailsReceiver() //Should take caseId from calls List onClick(Int) -> Unit
        observe()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onClicks(){
        binding.apply {
            caseDetailsBackArrowIV.setOnClickListener { caseDetailsBackArrowIV.onBackPressed() }
        }
    }

    private fun observe(){
        mCaseDetailsViewModel.caseDetailsLiveData.observe(this, {
            when(it.status){
                NetworkState.Status.RUNNING -> { showToastShort("Loading...") }

                NetworkState.Status.SUCCESS -> {
                val myData = it.data as? ModelCaseDetails
                    _dataOfCaseDetails = myData
                    val patient_name = dataOfCaseDetails?.data?.patient_name.toString().trim()
                    val age = dataOfCaseDetails?.data?.age.toString().trim()
                    val phoneNum = dataOfCaseDetails?.data?.phone.toString().trim()
                    val date = dataOfCaseDetails?.data?.created_at.toString().trim()
                    val caseStatus = dataOfCaseDetails?.data?.case_status.toString().trim()
                    val caseDescription = dataOfCaseDetails?.data?.description.toString().trim()
                    initDataToViews(patient_name, age, phoneNum, date, caseStatus, caseDescription)
                }

                NetworkState.Status.FAILED -> { showToastShort("${it.msg}") }
            }
        })
    }

    private fun initDataToViews(patient_name: String, age: String, phoneNum: String, date: String, caseStatus: String, caseDescription: String){
        binding.apply {
            caseDetailsPatientNameTV.text = patient_name
            caseDetailsAgeTV.text = age+" years"
            caseDetailsPhoneNumTV.text = phoneNum
            caseDetailsDateTV.text = date
            caseDetailsStatusProcessTV.text = caseStatus
            caseDetailsDescriptionTV.text = caseDescription

            if (caseStatus.equals("Process") || caseStatus.equals("process")){
                caseDetailsStatusIconIV.setImageResource(R.drawable.ic_red_mark)
                materialButton2.setBackgroundColor(resources.getColor(R.color.case_details_button_red))
                materialButton2.text = getString(R.string.button_status_is_active)
                materialButton2.icon = resources.getDrawable(R.drawable.ic_signout_door)
                materialButton2.isClickable = true
                materialButton2.visibility = View.VISIBLE
            }
            else {
                caseDetailsStatusIconIV.setImageResource(R.drawable.ic_green_mark)
                materialButton2.text = getString(R.string.button_status_is_loggedout)
                materialButton2.setTextColor(resources.getColor(R.color.case_details_button_text_grey))
                materialButton2.setBackgroundColor(resources.getColor(R.color.case_details_button_grey))
                materialButton2.visibility = View.VISIBLE
                materialButton2.isClickable = false
            }
        }
    }
}