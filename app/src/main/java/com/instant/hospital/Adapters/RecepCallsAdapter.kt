package com.instant.hospital.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instant.hospital.Models.CallsData
import com.instant.hospital.R

class RecepCallsAdapter(val onClicks: (Int?) -> Unit) : RecyclerView.Adapter<RecepCallsAdapter.MyRecepCallsHolder>() {

    private var mList: List<CallsData> = ArrayList()

    fun setRecepCallsListToAdapter(list: List<CallsData>){
        this.mList = list
        notifyItemChanged(list.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecepCallsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receptionist_calls, parent, false)
        return MyRecepCallsHolder(view)
    }

    override fun onBindViewHolder(holder: MyRecepCallsHolder, position: Int) {
        holder.name.text = mList[position].patient_name
        holder.date.text = mList[position].created_at
        if (mList[position].status.equals("process") || mList[position].status.equals("Process")){
            holder.callStatusImg.setImageDrawable(holder.callStatusImg.resources.getDrawable(R.drawable.ic_red_mark))
        }
        else {
            holder.callStatusImg.setImageDrawable(holder.callStatusImg.resources.getDrawable(R.drawable.ic_green_mark))
        }
    }

    override fun getItemCount(): Int = if (mList.size == null) 0 else mList.size

    inner class MyRecepCallsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.item_RecepCalls_name_TV)
        val date: TextView = itemView.findViewById(R.id.item_RecepCalls_date_TV)
        val callStatusImg: ImageView = itemView.findViewById(R.id.item_RecepCalls_callstatus_IV)

        init {
            itemView.setOnClickListener {
                onClicks.invoke(mList[layoutPosition].id)
            }
        }

    }

}