package com.instant.hospital.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.instant.hospital.R

class EmpListSearchTypeAdapter(val OnClick: (String?) -> Unit) :
    RecyclerView.Adapter<EmpListSearchTypeAdapter.MySearchTypeHolder>() {

    var mList: ArrayList<String>? = null
    var rowIndex = 0


    fun setData(list: ArrayList<String>?) {
        this.mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySearchTypeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_btn_search_type, parent, false)
        return MySearchTypeHolder(view)
    }

    override fun onBindViewHolder(holder: MySearchTypeHolder, position: Int) {

        holder.searchType.text = mList?.get(position)

        if (rowIndex == position){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.searchType.background = (getDrawable(holder.myContext!!, R.drawable.rounded_gray_strock))
                holder.searchType.setTextColor(getColor(holder.myContext!!, R.color.white))
                holder.searchType.setBackgroundColor(getColor(holder.myContext!!, R.color.main_color_Green))
            } else {
                holder.searchType.background = (getDrawable(holder.myContext!!, R.drawable.rounded_gray_strock))
                holder.searchType.setTextColor(getColor(holder.myContext!!, R.color.white))
                holder.searchType.setBackgroundColor(getColor(holder.myContext!!, R.color.main_color_Green))
            }
        }

        else {
            holder.searchType.setTextColor(getColor(holder.myContext!!, R.color.black))
            holder.searchType.setBackgroundColor(getColor(holder.myContext!!, R.color.white))
            holder.searchType.background = (getDrawable(holder.myContext!!, R.drawable.rounded_gray_strock))
        }

    }

    override fun getItemCount(): Int = if (mList == null) 0 else mList?.size!!


    inner class MySearchTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchType: TextView = itemView.findViewById(R.id.search_type_Temp)
        var myContext : Context?= null

        init {
            myContext = itemView.context
            itemView.setOnClickListener {
                rowIndex = layoutPosition
                OnClick.invoke(mList?.get(layoutPosition))
                notifyDataSetChanged()
            }
        }
    }

}


