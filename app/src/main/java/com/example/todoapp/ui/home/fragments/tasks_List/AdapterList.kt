package com.example.todoapp.ui.home.fragments.tasks_List

import android.graphics.Color
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
//        if (tasks!![position].isDone == true) {
//            holder.itemBinding.todoCheck.setBackgroundColor(Color.GREEN)
//            holder.itemBinding.todoTitleText.setTextColor(Color.GREEN)
//        }


        if (onItemDeleteListener != null) {
            holder.itemBinding.deleteItem.setOnLongClickListener {
                holder.itemBinding.swipeLayout.close(true)
                onItemDeleteListener?.onItemClick(position, tasks!![position])
                true
            }
        }
        if (onItemUpdateListener != null) {
            holder.itemBinding.todoCheck.setOnClickListener {
                onItemUpdateListener?.onItemClick(position, tasks!![position])
            }

        }

    }

    var onItemUpdateListener: OnItemClickListener? = null
    var onItemDeleteListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(position: Int, task: Task)
    }

    override fun getItemCount(): Int = tasks?.size ?: 0
    fun bindTasks(tasks: MutableList<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    fun tasksDeleted(task: Task) {
        //var position=tasks?.indexOf(task)
        tasks?.remove(task)
        //  notifyItemChanged(position!!)
        notifyDataSetChanged()
    }

//    fun taskUpdated(task: Task) {
//        tasks.add
//
//    }


    class ViewHolder(val itemBinding: TaskItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(task: Task) {
            itemBinding.todoTitleText.text = task.title
            itemBinding.todoDesc.text = task.description
            if (task.isDone == true) {
                itemBinding.todoCheck.setBackgroundColor(Color.GREEN)
                itemBinding.todoTitleText.setTextColor(Color.GREEN)
            }
        }


    }
}