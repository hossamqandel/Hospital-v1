package com.instant.hospital.Ui.Fragments.HR.NewUser

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelUser
import com.instant.hospital.R
import com.instant.hospital.Utils.gText
import com.instant.hospital.Utils.showToastLong
import com.instant.hospital.Utils.showToastShort
import com.instant.hospital.databinding.FragmentCreateUserBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CreateUserFragment : Fragment() {

    private var _binding: FragmentCreateUserBinding? = null
    private val binding get() = _binding!!
    private val mCreateUsViewModel: CreateUserViewModel by viewModels()
    var mCalendar = Calendar.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDatePicker()
        onClicks()
        observe()
    }

    private fun observe() {
        mCreateUsViewModel.registerLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                NetworkState.Status.RUNNING -> {
                }
                NetworkState.Status.SUCCESS -> {
                    val userData = it.data as ModelUser
                    showToastShort(userData.message)
                    showToastShort(userData.data.access_token)
                }

                else -> {
                    showToastShort(it.status)
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onClicks() {
        binding.apply {
            createUserCreateBTN.setOnClickListener { validation() }
            createUserDateArrowBTN.setOnClickListener { initDatePicker() }
            createUserBackArrowIV.setOnClickListener { requireActivity().onBackPressed() }
        }
    }


    private fun validation() {
        binding.apply {
            val email = createUserEmailET.text.toString().trim()
            val password = createUserPassET.text.toString().trim()
            val first_name = createUserFNameET.text.toString().trim()
            val last_name = createUserLNameET.text.toString().trim()
            val gender = createUserGenderSpin.selectedItem.toString().trim()
            val specialist = createUserSpecialistSpin.selectedItem.toString().trim()
            val birthday = createUserBirthDateTV.text.toString().trim()
            val status = createUserStatusSpin.selectedItem.toString().trim()
            val address = createUserAddressET.text.toString().trim()
            val mobile = createUserPhonNumET.text.toString().trim()
            val type = createUserSpecialistSpin.selectedItem.toString().trim()

            if (email.isEmpty()) {
                createUserEmailET.error = getString(R.string.required)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                createUserEmailET.error = getString(R.string.enter_the_email_correctly)
            } else if (password.isEmpty()) {
                createUserPassET.error = getString(R.string.required)
            } else if (first_name.isEmpty()) {
                createUserFNameET.error = getString(R.string.required)
            } else if (last_name.isEmpty()) {
                createUserLNameET.error = getString(R.string.required)
            } else if (gender.equals("Gender")) {
                showToastShort(getString(R.string.select_gender_type))
            } else if (specialist.equals("Specialist")) {
                showToastShort(getString(R.string.select_specialist_type))
            } else if (birthday.isEmpty()) {
                createUserBirthDateTV.error = getString(R.string.required)
            } else if (status.equals("Status")) {
                showToastShort(getString(R.string.select_status_type))
            } else if (address.isEmpty()) {
                createUserAddressET.error = getString(R.string.required)
            } else if (mobile.isEmpty()) {
                createUserPhonNumET.error = getString(R.string.required)
            } else if (type.equals("Specialist") || type.equals("")) {
                showToastShort(getString(R.string.select_specialist_type))
            } else {
                mCreateUsViewModel.createNewUser(
                    email,
                    password,
                    first_name,
                    last_name,
                    gender,
                    specialist,
                    birthday,
                    status,
                    address,
                    mobile,
                    type
                )
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

        binding.createUserDateArrowBTN.setOnClickListener {
            DatePickerDialog(
                requireContext(), datePicker,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun updateLabel(mCalendar: Calendar) {
        val myFormat = "MM-dd-yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.createUserBirthDateTV.text = sdf.format(mCalendar.time)
    }


}