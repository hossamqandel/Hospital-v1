package com.instant.hospital.Ui.Fragments.Common

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.instant.hospital.Adapters.TasksAdapter
import com.instant.hospital.Data.Network.NetworkState
import com.instant.hospital.Models.ModelAllTasks
import com.instant.hospital.R
import com.instant.hospital.Utils.onBackPressed
import com.instant.hospital.databinding.FragmentTasksBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TasksFragment : Fragment() {
    //TODO this screen almost done .. just check on it again when Backend team fix collection and endPoints errors then test it again
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private val mTasksAdapter by lazy { TasksAdapter() }
    var mCalendar = Calendar.getInstance()
    private val mTasksViewModel: TasksViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
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
            tasksBackArrowIV.setOnClickListener { tasksBackArrowIV.onBackPressed() }
        }
    }

    private fun observe(){
        binding.apply {
            mTasksViewModel.tasksLiveData.observe(this@TasksFragment, {
                when(it.status){
                    NetworkState.Status.RUNNING -> {}
                    NetworkState.Status.SUCCESS -> {
                        val data = it.data as? ModelAllTasks
                        mTasksAdapter.setAdapterList(data?.data!!)
                        tasksRecycler.adapter = mTasksAdapter
                    }
                    NetworkState.Status.FAILED -> {}
                }
            })
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

        binding.tasksCalenderDialog.setOnClickListener {
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
        val mDate = sdf.format(mCalendar.time)
        binding.tasksSearchByDateTV.text = mDate
        mTasksViewModel.getAllCallsByDate(mDate)
    }

}