package com.crisspian.roomexampletodo_04_09

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.crisspian.roomexampletodo_04_09.database.Task
import kotlinx.android.synthetic.main.task_item_list.view.*



class TaskAdapter(var mPassTheData: PassTheData) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var dataList = emptyList<Task>()

    fun updateDataList(mDataList: List<Task>){
        dataList = mDataList
        notifyDataSetChanged()
    }


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var textViewa = itemView.textView
        var textViewb = itemView.textView2
        var boxtexta = itemView.checkBox
        var itemView = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
            mPassTheData.passTheData((dataList[adapterPosition]))
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item_list, parent, false)
        return TaskViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var task : Task = dataList[position]
        holder.textViewa.text = task.task
        holder.textViewb.text = task.id.toString()
        holder.boxtexta.isChecked = task.completeTask
    }

    override fun getItemCount() = dataList.size

        interface PassTheData{
            fun passTheData(mTask: Task)
        }


}