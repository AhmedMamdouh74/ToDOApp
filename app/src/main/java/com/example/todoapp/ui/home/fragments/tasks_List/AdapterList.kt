package com.example.todoapp.ui.home.fragments.tasks_List

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todoapp.R
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.TaskItemRecyclerBinding
import com.zerobranch.layout.SwipeLayout
import com.zerobranch.layout.SwipeLayout.SwipeActionsListener

class AdapterList(var tasks: MutableList<Task>?) : RecyclerView.Adapter<AdapterList.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemBinding =
            TaskItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks!![position])

        if (onItemDeleteListener != null) {
            holder.itemBinding.deleteItem.setOnLongClickListener {
                holder.itemBinding.swipeLayout.close(true)
                onItemDeleteListener?.onItemDelete(position, tasks!![position])
           true }         }

    }

    var onItemDeleteListener: OnItemDeleteListener? = null

    fun interface OnItemDeleteListener {
        fun onItemDelete(position: Int, task: Task)
    }

    override fun getItemCount(): Int = tasks?.size ?: 0
    fun bindTasks(tasks:MutableList<Task>) {
        this.tasks = tasks
       notifyDataSetChanged()
    }

    fun tasksDeleted(task: Task) {
       // var position=tasks?.indexOf(task)
        tasks?.remove(task)
       // notifyItemChanged(position!!)
        notifyDataSetChanged()
    }



    class ViewHolder(val itemBinding: TaskItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(task: Task) {
            itemBinding.todoTitleText.text = task.title
            itemBinding.todoDesc.text = task.description
        }


    }
}