package com.instant.hospital.Ui.Fragments.HR.Specialist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.instant.hospital.R
import com.instant.hospital.databinding.FragmentSpecialistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialistFragment : Fragment() {

    var _binding: FragmentSpecialistBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSpecialistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}