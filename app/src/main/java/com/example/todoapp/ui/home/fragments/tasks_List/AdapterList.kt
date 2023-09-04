package com.example.todoapp.ui.home.fragments.tasks_List

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todoapp.R
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.TaskItemRecyclerBinding

class AdapterList(var tasks: List<Task>?) : RecyclerView.Adapter<AdapterList.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemBinding =
            TaskItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks!![position])

    }

    override fun getItemCount(): Int = tasks?.size ?: 0
    fun bindTasks(tasks: List<Task>) {
        this.tasks = tasks
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