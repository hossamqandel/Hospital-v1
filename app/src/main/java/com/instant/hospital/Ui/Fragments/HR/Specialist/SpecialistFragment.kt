package com.instant.hospital.Ui.Fragments.HR.Specialist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.R
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.databinding.FragmentSpecialistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialistFragment : Fragment() , View.OnClickListener {

    var _binding: FragmentSpecialistBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSpecialistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            specialistEmployeeCard.setOnClickListener(this@SpecialistFragment)
//            specialistEmployeeCard.setOnClickListener{this}
//            specialistEmployeeCard.setOnClickListener{this}
//            specialistEmployeeCard.setOnClickListener{this}
            cardViewIV.setOnClickListener(this@SpecialistFragment)
        }

        initUserInfoFromSharedPref()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.specialist_Employee_Card -> { setNavigation(R.id.action_specialistFragment_to_employeeListFragment) }
            R.id.specialist_Tasks_Card -> {}
            R.id.specialist_Reports_Card -> {}
            R.id.specialist_AttLeav_Card -> {}
            R.id.cardView_IV -> {setNavigation(R.id.action_specialistFragment_to_profileFragment)}
        }
    }

    private fun initUserInfoFromSharedPref(){
        binding.apply {
            val userName = MySharedPref.getUserName()
            specialistUserNameTV.text = "$userName"

            val userType = MySharedPref.getUserType()
            specialistUserSpecialistTV.text = "Specialist , $userType"
        }
    }

}