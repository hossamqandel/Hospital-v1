package com.instant.hospital.Ui.Fragments.Common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelCaseDetails
import com.instant.hospital.R
import com.instant.hospital.Utils.*
import com.instant.hospital.databinding.FragmentDocNurseCaseDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocNurseCaseDetailsFragment : Fragment() {

    private var _binding: FragmentDocNurseCaseDetailsBinding? = null
    private val binding get() = _binding!!
    private val mDoctorCaseViewModel: DoctorCaseViewModel by viewModels()
    private var _callId: Int? = null
    private val callId get() = _callId

    private var _status: String? = null
    private val status get() = _status


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDocNurseCaseDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClicks()
        handlingCommonViewsBetweenDoctorAndNurse()
        initViewsData()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicks(){
        binding.apply {
            docNurseCaseDetailsCloseBTN.setOnClickListener {
                docNurseCaseDetailsRequestCard.makeGone()
                textView2.setMargins(null, 100, null, null)
            }
            docNurseCaseDetailsAddNurseBTN.setOnClickListener { setNavigation(R.id.action_docNurseCaseDetailsFragment_to_docSelectNurseFragment) }
            docNurseCaseDetailsBackArrowIV.setOnClickListener { docNurseCaseDetailsBackArrowIV.onBackPressed() }
            docNurseCaseDetailsMedicalMesurBTN.setOnClickListener { findNavController().navigate(DocNurseCaseDetailsFragmentDirections.actionDocNurseCaseDetailsFragment2ToAddMeasurementFragment(callId!!, status!!)) }

        }
    }

    private fun handlingCommonViewsBetweenDoctorAndNurse(){
        binding.apply {

            if (docNurseCaseDetailsRequestCard.isVisible){
                docNurseCaseDetailsRequestCard.setMargins(null, 30, null, null)
                textView2.setMargins(null, 600, null, null)
            }

            if (MySharedPref.getUserType().equals("Nurse") || MySharedPref.getUserType().equals("nurse")){
                docNurseCaseDetailsMedicalRecBTN.makeGone()
                docNurseCaseDetailsAddNurseBTN.makeGone()
                doctorCaseDetailsAddRequestBTN.makeGone()
                docNurseCaseDetailsEndCaseOrCallDocBTN.text = "Call Doctor"
                docNurseCaseDetailsEndCaseOrCallDocBTN.setBackgroundColor(resources.getColor(R.color.main_color_Green))
            }

            else if (MySharedPref.getUserType().equals("Doctor") || MySharedPref.getUserType().equals("doctor")){
                docNurseCaseDetailsRequestCard.makeGone()
                textView2.setMargins(null, 80, null, null)

            }
        }
    }


    private fun initViewsData(){
        mDoctorCaseViewModel.getCaseDetailsById()
        binding.apply {

            mDoctorCaseViewModel.doctorCaseLiveData.observe(this@DocNurseCaseDetailsFragment, {
                when(it.status){
                    NetworkState.Status.RUNNING -> {}
                    NetworkState.Status.SUCCESS -> {
                        val model = it.data as? ModelCaseDetails
                        val modelData = model?.data

                        _callId = modelData?.id
                        _status = modelData?.status
                        docNurseCaseDetailsPatientNameTV.text = modelData?.patient_name.toString().trim()
                        doctorCaseDetailsAgeTV.text = "${modelData?.age.toString().trim()} years"
                        docNurseCaseDetailsPhoneNumTV.text = modelData?.phone.toString().trim()
                        docNurseCaseDetailsDateTV.text = modelData?.created_at.toString().trim()
                        doctorCaseDetailsDocNameTV.text = modelData?.doctor_id.toString().trim()
                        doctorCaseDetailsNurseNameTV.text = modelData?.nurse_id.toString().trim()
                        docNurseCaseDetailsStatusProcessTV.text = modelData?.case_status.toString().trim()
                        docNurseCaseDetailsDescriptionTV.text = modelData?.description.toString().trim()

                        if (modelData?.case_status.toString().trim().equals("process") || modelData?.case_status.toString().trim().equals("Process")){
                            docNurseCaseDetailsStatusIconIV.setImageDrawable(resources.getDrawable(R.drawable.ic_red_mark))
                        }
                        else {
                            docNurseCaseDetailsStatusIconIV.setImageDrawable(resources.getDrawable(R.drawable.ic_green_mark))
                        }
                    }
                    NetworkState.Status.FAILED -> {}
                }
            })
        }
    }
}