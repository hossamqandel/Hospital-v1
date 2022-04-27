package com.instant.hospital.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.instant.hospital.Models.TasksData
import com.instant.hospital.R


class TasksAdapter : RecyclerView.Adapter<TasksAdapter.MyTasksHolder>() {

    private var mList: List<TasksData> = ArrayList<TasksData>()

    fun setAdapterList(list: List<TasksData>){
        this.mList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.MyTasksHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false)
        return MyTasksHolder(view)
    }

    override fun onBindViewHolder(holder: TasksAdapter.MyTasksHolder, position: Int) {
        holder.taskName.text = mList[position].task_name
        holder.date.text = mList[position].created_at
        holder.taskStatus.text = mList[position].status

        if (mList[position].status.equals("Finished") || mList[position].status.equals("finished")){
            holder.card.setCardBackgroundColor(holder.card.resources.getColor(R.color.tasks_light_finished))
            holder.taskStatus.setTextColor(holder.taskStatus.resources.getColor(R.color.tasks_green_text))
        } else {
            holder.card.setCardBackgroundColor(holder.card.resources.getColor(R.color.tasks_light_proceess))
            holder.taskStatus.setTextColor(holder.taskStatus.resources.getColor(R.color.tasks_orange_text))

        }
    }

    override fun getItemCount(): Int = if (mList.size == null) 0 else mList.size

    inner class MyTasksHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.item_Tasks_name_TV)
        val date: TextView = itemView.findViewById(R.id.item_Tasks_date_TV)
        val card: CardView = itemView.findViewById(R.id.item_Tasks_tasksStatus_Card)
        val taskStatus: TextView = itemView.findViewById(R.id.item_Tasks_tasksStatus_TV)
    }
}