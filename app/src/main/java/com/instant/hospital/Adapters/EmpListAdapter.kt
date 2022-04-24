package com.instant.hospital.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instant.hospital.Models.Data
import com.instant.hospital.Models.UserData
import com.instant.hospital.R
import javax.inject.Singleton

@Singleton
class EmpListAdapter : RecyclerView.Adapter<EmpListAdapter.MyEmpListHolder>() {

    private var mList: List<UserData> = ArrayList<UserData>()

    fun setAdapterList(list: List<UserData>){
        this.mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEmpListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee_list, parent, false)
        return MyEmpListHolder(view)
    }

    override fun onBindViewHolder(holder: MyEmpListHolder, position: Int) {

        holder.doctorName.text = mList[position].first_name
        holder.type.text = mList[position].type
        Glide.with(holder.doctorPhoto.context).load(mList[position].avatar).into(holder.doctorPhoto);

    }

    override fun getItemCount(): Int = if (mList.size == null) 0 else mList.size

    inner class MyEmpListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorName: TextView = itemView.findViewById(R.id.item_employeeName_TV)
        val type: TextView = itemView.findViewById(R.id.item_employeeType_TV)
        val doctorPhoto: ImageView = itemView.findViewById(R.id.item_employeePhoto_IV)
    }

}