package com.instant.hospital.Ui.Fragments.Doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Adapters.SelectEmployeeAdapter
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelAllUsers
import com.instant.hospital.Ui.Fragments.Receptionist.SelectDoctor.SelectEmployeeViewModel
import com.instant.hospital.databinding.FragmentDocSelectNurseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocSelectNurseFragment : Fragment() {

    //TODO (Handle Selected Nurse Request and add his own webServices Funcation After that popBackStack)
    private var _binding: FragmentDocSelectNurseBinding? = null
    private val binding get() = _binding!!

    private val mSeleDocViewModel: SelectEmployeeViewModel by viewModels()
    private var mSeleDocAdapter: SelectEmployeeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDocSelectNurseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSeleDocViewModel.getAllNurses()

        mSeleDocAdapter = SelectEmployeeAdapter { nurseName: String?, nurseId: Int? ->
        }

        observe()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observe(){
        mSeleDocViewModel.loadDoctorsLiveData.observe(this, {
            when(it.status){
                NetworkState.Status.RUNNING -> {}

                NetworkState.Status.SUCCESS -> {
                    val listOfUserData = it.data as ModelAllUsers
                    mSeleDocAdapter?.setAdapterList(listOfUserData.data)
                    binding.selectNurseRecycler.adapter = mSeleDocAdapter
                }

                NetworkState.Status.FAILED -> {}
            }
        })
    }



}