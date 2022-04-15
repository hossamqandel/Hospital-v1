package com.instant.hospital.Ui.Fragments.HR.EmployeeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instant.hospital.Adapters.EmpListSearchTypeAdapter
import com.instant.hospital.databinding.FragmentEmployeeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {

    var _binding: FragmentEmployeeListBinding? = null
    val binding get() = _binding!!
    var mList: ArrayList<String>? = null
    lateinit var mAdapter: EmpListSearchTypeAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        val view = binding.root
        filtertypes()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = EmpListSearchTypeAdapter {
        }

        mAdapter.setData(mList!!)
    binding.employListBtntypesRecycler.adapter = mAdapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filtertypes(){
        mList = ArrayList()
        mList?.add("All")
        mList?.add("Doctor")
        mList?.add("Nurse")
        mList?.add("HR Employee")
        mList?.add("Analysis Employee")
    }
}