package com.instant.hospital.Ui.Fragments.Receptionist.SelectDoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.instant.hospital.Adapters.SelectDoctorAdapter
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelAllUsers
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.databinding.FragmentSelectDoctorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectDoctorFragment : Fragment() {

    private var _binding: FragmentSelectDoctorBinding? = null
    private val binding get() = _binding!!
    private val mSeleDocViewModel: SelectDoctorViewModel by viewModels()
    private var mSeleDocAdapter: SelectDoctorAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSelectDoctorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapterHandling()
        observe()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        mSeleDocViewModel.getAllDoctors()
        mSeleDocViewModel.loadDoctorsLiveData.observe(this, {
            when(it.status){
                NetworkState.Status.RUNNING -> {

                }

                NetworkState.Status.SUCCESS -> {
                    val listOfUserData = it.data as ModelAllUsers
                    mSeleDocAdapter?.setAdapterList(listOfUserData.data)
                    binding.selectDoctorRecycler.adapter = mSeleDocAdapter
                }

                NetworkState.Status.FAILED -> {

                }
            }
        })
    }
    private fun adapterHandling(){
        mSeleDocAdapter = SelectDoctorAdapter { doctorName: String?, doctorId: Int? ->
            if (doctorName != null) {
                if (doctorId != null) {
                    onClicks(doctorName, doctorId)
                }
            }
        }
    }
    private fun onClicks(doctorName: String, doctorId: Int){
        binding.apply {
            materialButton.setOnClickListener {
                val action = SelectDoctorFragmentDirections.actionSelectDoctorFragmentToCreateCallFragment(doctorName!!, doctorId!!)
                findNavController().navigate(action)
            }

            selectDoctorBackArrowIV.setOnClickListener { selectDoctorBackArrowIV.onBackPressed() }

        }
    }
}