package com.instant.hospital.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.instant.hospital.Data.Local.MySharedPref
import com.instant.hospital.Models.CallsData
import com.instant.hospital.Models.CaseData
import com.instant.hospital.R
import com.instant.hospital.Utils.setMargins

class CommonCallsAdapter(val onClick: (String) -> Unit): RecyclerView.Adapter<CommonCallsAdapter.MyCommonCallsHolder>() {

    private var mList: List<CallsData> = ArrayList()

    fun setCommonCallsListToAdapter(list: List<CallsData>){
        this.mList = list
        notifyItemChanged(list.size - 1)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommonCallsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_common_calls, parent, false)
        return MyCommonCallsHolder(view)
    }

    override fun onBindViewHolder(holder: MyCommonCallsHolder, position: Int) {
        // TODO : handle doctor name when display all calls endPoint fixed
        holder.patientName.text = mList[position].patient_name
//        holder.doctorName.text = mList[position].d
        holder.date.text = mList[position].created_at
        // TODO : TEST Ui after margins and views visibility
        if (MySharedPref.getUserType().equals("Doctor") || MySharedPref.getUserType().equals("doctor")){
            holder.doctorName.visibility = View.GONE
            holder.doctorIcon.visibility = View.GONE
            holder.date.setMargins(null, 8, null, null)
        }

        else {
            holder.doctorName.visibility = View.VISIBLE
            holder.doctorIcon.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int = if (mList.size == null) 0 else mList.size

    inner class MyCommonCallsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName = itemView.findViewById<TextView>(R.id.item_commonCalls_PatientName_TV)
        val doctorName = itemView.findViewById<TextView>(R.id.item_commonCalls_DoctorName_TV)
        val date = itemView.findViewById<TextView>(R.id.item_commonCalls_Date_TV)
        val acceptButton = itemView.findViewById<MaterialButton>(R.id.item_commonCalls_Accept_BTN)
        val busyButton = itemView.findViewById<MaterialButton>(R.id.item_commonCalls_Busy_BTN)
        val doctorIcon = itemView.findViewById<ImageView>(R.id.imageView221417)


        init {
            acceptButton.setOnClickListener {
                onClick.invoke(acceptButton.text[layoutPosition].toString())
            }

            busyButton.setOnClickListener {
                onClick.invoke("reject"[layoutPosition].toString())
            }
        }

    }

}