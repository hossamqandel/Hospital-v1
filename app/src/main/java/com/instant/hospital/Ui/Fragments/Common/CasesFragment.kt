package com.instant.hospital.Ui.Fragments.Common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instant.hospital.R
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.databinding.FragmentCasesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CasesFragment : Fragment() {

    private var _binding: FragmentCasesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCasesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicksss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicksss(){
        binding.apply {
            casesBackArrowIV.setOnClickListener { casesBackArrowIV.onBackPressed() }
            myButton.setOnClickListener { setNavigation(R.id.action_casesFragment_to_docNurseCaseDetailsFragment) }
        }
    }



}