package com.instant.hospital.Ui.Fragments.Receptionist.RecepHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.R
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.databinding.FragmentReceptionistHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceptionistHomeFragment : Fragment() {

    private var _binding: FragmentReceptionistHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReceptionistHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        getUserDataFromSharedPref()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onClicks(){
        binding.apply {
            receptionistUserImgIV.setOnClickListener { setNavigation(R.id.action_receptionistHomeFragment_to_profileFragment) }
            receptionistCallsCard.setOnClickListener { setNavigation(R.id.action_receptionistHomeFragment_to_receptionistCallsFragment) }
        }
    }
    private fun getUserDataFromSharedPref(){
        binding.apply {
            receptionistUserNameTV.text = MySharedPref.getUserName()
            receptionistUserSpecialistTV.text = "Specialist - ${MySharedPref.getUserType()}"
        }
    }

}