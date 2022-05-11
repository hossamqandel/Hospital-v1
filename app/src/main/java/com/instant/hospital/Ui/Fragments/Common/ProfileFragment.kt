package com.instant.hospital.Ui.Fragments.Common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUserDataToProfile()
        onClicks()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicks(){
        binding.apply {
            commonBackArrowIV.setOnClickListener { commonBackArrowIV.onBackPressed() }
        }
    }
    private fun initUserDataToProfile(){
        binding.apply {

            val fullName = MySharedPref.getUserName()
            commonUserNameIV.text = "$fullName"

            val specialist = MySharedPref.getUserType()
            commonSpecialistTV.text = "Specialist - $specialist"

            val gender = MySharedPref.getUserGender()
            commonGenderTV.text = "$gender"

            val birthDate = MySharedPref.getUserBirthDate()
            commonBirthDateTV.text = "$birthDate"

            val address = MySharedPref.getUserAddress()
            commonAddressTV.text = "$address"

            val status = MySharedPref.getUserStatus()
            commonStatusTV.text = "$status"


            val email = MySharedPref.getUserEmail()
            commonEmailTV.text = "$email"


            val phoneNumber = MySharedPref.getUserPhone()
            commonPhNumTV.text = "$phoneNumber"

        }
    }

}