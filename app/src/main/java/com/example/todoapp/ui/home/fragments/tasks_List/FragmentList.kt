package com.example.todoapp.ui.home.fragments.tasks_List

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todoapp.constance.constance
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.FragmentTasksListBinding
import com.example.todoapp.ui.edit.EditActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar

class FragmentList : Fragment() {
    lateinit var viewBinding: FragmentTasksListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTasksListBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        loadTasksFromDataBase()
    }


    fun loadTasksFromDataBase() {
        context?.let {
            val tasks =
              //  TodoDatabase.getInstance(it).getTodosDao().getAllTasks()
                TodoDatabase.getInstance(it).getTodosDao().getTasksByDate(selectedDay.timeInMillis)



            adapterList.bindTasks(tasks.toMutableList())

        }

    }

    fun deleteTasksFromDataBase(task: Task) {
        TodoDatabase.getInstance(requireContext())
            .getTodosDao()
            .deleteTodo(task)
        Toast.makeText(requireContext(), "Task Deleted !!", Toast.LENGTH_LONG).show()
    }

    private val adapterList = AdapterList(null)
    val selectedDay = Calendar.getInstance()

    init {
        selectedDay.set(Calendar.HOUR_OF_DAY, 0)
        selectedDay.set(Calendar.MINUTE, 0)
        selectedDay.set(Calendar.SECOND, 0)
        selectedDay.set(Calendar.MILLISECOND, 0)
    }

    private fun initViews() {
        viewBinding.recyclerView.adapter = adapterList
        adapterList.onItemDeleteListener = AdapterList.OnItemClickListener { position, task ->
            deleteTasksFromDataBase(task)
            adapterList.tasksDeleted(task)
        }
        viewBinding.calendarView.setSelectedDate(
            CalendarDay.today()
        )
        viewBinding.calendarView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            if (selected) {
                selectedDay.set(Calendar.YEAR, date.year)
                selectedDay.set(Calendar.MONTH, date.month - 1)
                selectedDay.set(Calendar.DAY_OF_MONTH, date.day)
                // load tasks from selected date
                loadTasksFromDataBase()
            }
        })
        adapterList.onItemUpdateListener = AdapterList.OnItemClickListener { position, task ->
            makeDone(task)
        }
        adapterList.onItemEditListener = AdapterList.OnItemClickListener { position, task ->
            updateTask(task)

        }
        adapterList.onItemClickListener = AdapterList.OnItemClickListener { position, task ->
            updateTask(task)
        }


    }

    fun makeDone(task: Task) {
        task.isDone = true
        TodoDatabase.getInstance(requireContext())
            .getTodosDao().updateTodo(task)
        adapterList.notifyDataSetChanged()
    }


    private fun updateTask(task: Task) {
        val intent = Intent(requireContext(), EditActivity::class.java)
        intent.putExtra(constance.task_key, task)
        startActivity(intent)
    }

}








