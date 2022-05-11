package com.instant.hospital.Ui.Fragments.Common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Adapters.CommonCallsAdapter
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.CallsData
import com.instant.hospital.Models.CaseData
import com.instant.hospital.Models.ModelAllCalls
import com.instant.hospital.R
import com.instant.hospital.Utils.showToastShort
import com.instant.hospital.databinding.FragmentCallsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallsFragment : Fragment() {

    private var _binding: FragmentCallsBinding? = null
    private val binding get() = _binding!!
    private var mAdapter: CommonCallsAdapter? = null
    private val mCommonCallsViewModel: CommonCallsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCallsBinding.inflate(inflater, container, false)
        val view = binding.root
        mAdapter = CommonCallsAdapter {
            // TODO : throw PUT Request (accept - reject) fun and make it take the type of answer from 'it' lambda experission
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicks(){

    }

    private fun observe(){
        mCommonCallsViewModel.getCalls("2021-05-09") // Temporary date
        mCommonCallsViewModel.commonCallsLiveData.observe(this, {
            when(it.status){
                NetworkState.Status.RUNNING -> {}

                NetworkState.Status.SUCCESS -> {
                    var mData = it.data as? ModelAllCalls
                    mAdapter?.setCommonCallsListToAdapter(mData?.data!!)
                    binding.callsRecycler.adapter = mAdapter
                }

                NetworkState.Status.FAILED -> { showToastShort("${it.msg.toString()}" ) }
            }
        })
    }



}