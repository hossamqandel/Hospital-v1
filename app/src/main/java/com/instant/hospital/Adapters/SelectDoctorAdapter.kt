package com.instant.hospital.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instant.hospital.Models.Data
import com.instant.hospital.Models.UserData
import com.instant.hospital.R


class SelectDoctorAdapter(val onClick: (String?, Int?) -> Unit) : RecyclerView.Adapter<SelectDoctorAdapter.MySelectedDoctorHolder>() {

    private var lastCheckedPosition = -1

    private var mList: List<UserData>? = null

    fun setAdapterList(list: List<UserData>){
        this.mList = list
        notifyItemChanged(list.size - 1)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySelectedDoctorHolder {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.item_select_doctor, parent, false)
        return MySelectedDoctorHolder(view)
    }

    override fun onBindViewHolder(holder: MySelectedDoctorHolder, position: Int) {

            holder.name.text = mList?.get(position)?.first_name
            holder.type.text = mList?.get(position)?.type
            Glide.with(holder.photo.context).load(mList?.get(position)?.avatar).into(holder.photo)
            //status

        holder.selectedDocRB.isChecked = position == lastCheckedPosition

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = if (mList?.size == null) 0 else mList!!.size


    inner class MySelectedDoctorHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.item_selectedDoctorName_TV)
        val type: TextView = itemView.findViewById(R.id.item_selectedType_TV)
        val photo: ImageView = itemView.findViewById(R.id.item_selectedDoctorPhoto_IV)
        val status: ImageView = itemView.findViewById(R.id.item_selectedDoctorStatus_IV) //UnUsed Yet
        val selectedDocRB: RadioButton = itemView.findViewById(R.id.item_selectedDocotr_RB)

        init {

            selectedDocRB.setOnClickListener {
                val copyOfLastCheckedPosition = lastCheckedPosition
                lastCheckedPosition = adapterPosition
                notifyItemChanged(copyOfLastCheckedPosition)
                notifyItemChanged(lastCheckedPosition)
                onClick.invoke(mList?.get(layoutPosition)?.first_name, mList?.get(layoutPosition)?.id)
            }

            itemView.setOnClickListener {
                val copyOfLastCheckedPosition = lastCheckedPosition
                lastCheckedPosition = adapterPosition
                notifyItemChanged(copyOfLastCheckedPosition)
                notifyItemChanged(lastCheckedPosition)
                onClick.invoke(mList?.get(layoutPosition)?.first_name, mList?.get(layoutPosition)?.id)
            }
        }
    }

}