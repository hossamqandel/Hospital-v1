package com.instant.hospital.Ui.Fragments.Common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.R
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.databinding.FragmentDocNurseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocNurseFragment : Fragment() {


    private var _binding: FragmentDocNurseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDocNurseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsData()
        onClicks()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onClicks(){
        binding.apply {
            cardViewIV.setOnClickListener { setNavigation(R.id.action_docNurseFragment_to_profileFragment) }
        }
    }

    private fun initViewsData(){
        binding.apply {
            val docNurseName = MySharedPref.getUserName()
            docNurseUserNameTV.text = "$docNurseName"

            val docNurseType = MySharedPref.getUserType()
            docNurseUserSpecialistTV.text = "Specialist, $docNurseType"
        }
    }

}