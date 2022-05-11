package com.instant.hospital.Ui.Fragments.HR.EmployeeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.instant.hospital.Adapters.EmpListAdapter
import com.instant.hospital.Adapters.EmpListSearchTypeAdapter
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelAllUsers
import com.instant.hospital.Models.Data
import com.instant.hospital.Models.UserData
import com.instant.hospital.R
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.databinding.FragmentEmployeeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {

    var _binding: FragmentEmployeeListBinding? = null
    val binding get() = _binding!!
    var mList: ArrayList<String>? = null
    private val mEmpViewModel: EmployeeListViewModel by viewModels()
    lateinit var mSearchByTypeAdapter: EmpListSearchTypeAdapter
    lateinit var mEmpAdapter: EmpListAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.employeeListSearchView.clearFocus()
        initDataIntoSearchTypesList()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClicks()
        initSearchTypeAdapterWithList()
        observe()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onClicks(){
        binding.apply {
            employeeListAdd.setOnClickListener{ setNavigation(R.id.action_employeeListFragment_to_createUserFragment) }
            employeeListBackArrowIV.setOnClickListener { employeeListBackArrowIV.onBackPressed() }
        }
    }

    private fun observe(){
        mEmpViewModel.employeeListLiveData.observe(this, {
            when(it.status){
                NetworkState.Status.RUNNING -> {}
                NetworkState.Status.SUCCESS -> {
                    mEmpAdapter = EmpListAdapter()
                    val allUsersData = it.data as ModelAllUsers
                    mEmpAdapter.setAdapterList(allUsersData.data)
                    initSearchViewBar(allUsersData.data)
                    binding.employeeListRecycler.adapter = mEmpAdapter
                }
                NetworkState.Status.FAILED -> {}
            }
        })
    }

    private fun initSearchTypeAdapterWithList(){
        mSearchByTypeAdapter = EmpListSearchTypeAdapter {
            mEmpViewModel.getAllCalls("${it.toString().lowercase()}")
        }
        mSearchByTypeAdapter.setData(mList)
        binding.employListBtntypesRecycler.adapter = mSearchByTypeAdapter
    }

    private fun initSearchViewBar(list: List<UserData>){
        binding.employeeListSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterRecycler(newText, list)
                return false
            }
        })
    }

    private fun filterRecycler(newText: String?, list: List<UserData>){
        var filtredList = ArrayList<UserData>()

        for (model in list){
            if (model.first_name.lowercase().contains(newText!!.lowercase())){
                filtredList.add(model)
            }
        }

        if (filtredList.isEmpty()){
        }
        else {
            // at last we are passing that filtered
            // list to our adapter class.
            mEmpAdapter.setAdapterList(filtredList)
        }
    }

    private fun initDataIntoSearchTypesList(){
        mList = ArrayList()
        mList?.add("All")
        mList?.add("Doctor")
        mList?.add("Nurse")
        mList?.add("HR")
        mList?.add("Analysis")
    }


}