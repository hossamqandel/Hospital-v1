package com.instant.hospital.Ui.Fragments.Authentication

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelUser
import com.instant.hospital.R
import com.instant.hospital.Utils.*
import com.instant.hospital.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!
    private val mLoginViewModel: LoginViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.loginForgetPassTV.underline()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()


    }

    override fun onStart() {
        super.onStart()
        observe()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick() {
        binding.loginLoginBTN.setOnClickListener { validation() }
    }


    private fun validation() {
        binding.apply {
            val email = loginEmailET.text.toString().trim()
            val password = loginPasswordET.text.toString().trim()

            if (email.isEmpty()) {
                loginEmailET.error = getString(R.string.required)
                return
            } else if (password.isEmpty()) {
                loginPasswordET.error = getString(R.string.required)
                return
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                loginEmailET.error = getString(R.string.enter_the_email_correctly)
                return
            } else if (password.length < 8) {
                loginPasswordET.error = getString(R.string.password_length)
                return
            } else {
                mLoginViewModel.loginOrder(email, password)
            }
        }
    }

    private fun observe() {

        mLoginViewModel.loginLiveData.observe(this, {
            when (it.status) {

                NetworkState.Status.RUNNING -> {
                    showToastShort("RUNNING")
                }

                NetworkState.Status.SUCCESS -> {
                    val userData = it.data as ModelUser
                    MySharedPref.setUserName("${userData.data.first_name + " " + userData.data.last_name}")
                    MySharedPref.setUserType("${userData.data.specialist}")
                    MySharedPref.setUserGender("${userData.data.gender}")
                    MySharedPref.setUserBirthDate("${userData.data.birthday}")
                    MySharedPref.setUserAddress("${userData.data.address}")
                    MySharedPref.setUserStatus("${userData.data.status}")
                    MySharedPref.setUserEmail("${userData.data.email}")
                    MySharedPref.setUserPhone("${userData.data.mobile}")
                    MySharedPref.setUserId(userData.data.id)
                    MySharedPref.setUserSecretToken(userData.data.access_token)
                    navigateBasedOnUserType(userData.data.type)
                }

                NetworkState.Status.FAILED -> {
                    showToastShort("FAILED")
                }
            }
        })
    }

    private fun navigateBasedOnUserType(userType: String?) {
        when (userType) {
            Const.HR -> {
                setNavigation(R.id.action_loginFragment_to_specialistFragment2)
            }
            Const.DOCTOR -> {
                setNavigation(R.id.action_loginFragment_to_docNurseFragment)
            }
            Const.NURSE -> {
                setNavigation(R.id.action_loginFragment_to_docNurseFragment)
            }
            Const.MANGER -> {
//                setNavigation(R.id.action)
            }
            Const.RECEPTIONIST -> {
                setNavigation(R.id.action_loginFragment_to_receptionistHomeFragment)
            }
        }
    }


}