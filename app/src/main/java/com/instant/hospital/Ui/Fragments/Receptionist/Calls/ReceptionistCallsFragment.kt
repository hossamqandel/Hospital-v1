package com.instant.hospital.Ui.Fragments.Receptionist.Calls

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.instant.hospital.Adapters.RecepCallsAdapter
import com.instant.hospital.R
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.Utils.setNavigation
import com.instant.hospital.databinding.FragmentReceptionistCallsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ReceptionistCallsFragment : Fragment() {

    //TODO: not compeleted because of postman calls endpoint request return exception errors
    private var _binding: FragmentReceptionistCallsBinding? = null
    private val binding get() = _binding!!
    var mCalendar = Calendar.getInstance()
    private lateinit var mCallsAdapter: RecepCallsAdapter
    private val RecCallsViewModel: RecepCallsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReceptionistCallsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClicks()
        initDatePicker()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClicks(){
        binding.apply {
            callsBackArrowIV.setOnClickListener { callsBackArrowIV.onBackPressed() }
            callsAddCallIV.setOnClickListener { setNavigation(R.id.action_receptionistCallsFragment_to_createCallFragment) }
            mCallsAdapter = RecepCallsAdapter {
                findNavController()
                    .navigate(ReceptionistCallsFragmentDirections.actionReceptionistCallsFragmentToCaseDetailsFragment(it!!))
            }

        }
    }

    private fun initDatePicker() {
        val datePicker =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                mCalendar.set(Calendar.YEAR, year)
                mCalendar.set(Calendar.MONTH, monthOfYear)
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel(mCalendar)
            }

        binding.callsCalenderDialog.setOnClickListener {
            DatePickerDialog(
                requireContext(), datePicker,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun updateLabel(mCalendar: Calendar) {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.callsSearchByDateTV.text = sdf.format(mCalendar.time)
    }


    private fun observe(){
        val textViewDate = binding.callsSearchByDateTV.text.toString().trim()

        if (textViewDate.isEmpty()){
            RecCallsViewModel.getCallsByDate(textViewDate)
        }
        else {
            RecCallsViewModel.callsLiveData.observe(this, {
            })

        }


    }
}