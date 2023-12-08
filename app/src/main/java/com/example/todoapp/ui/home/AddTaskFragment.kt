package com.example.todoapp.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.ui.home.fragments.tasks_List.AdapterList
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var viewBinding: FragmentAddTaskBinding




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTaskBtn.setOnClickListener {

            createTask()


        }
        viewBinding.dateContainer.setOnClickListener {
            showDatePickerDialog()
        }


    }


    var calendar = Calendar.getInstance()
    private fun showDatePickerDialog() {

        context?.let {
            val dialog = DatePickerDialog(it)
            dialog.setOnDateSetListener { datePicker, year, month, dayOfMonth ->
                viewBinding.date.text = "$dayOfMonth / ${month+1} / $year"
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                // to ignore time
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)

            }

            dialog.show()
        }


    }

    private fun valid(): Boolean {
        var isValid = true
        if (viewBinding.title.text.toString().isNullOrBlank()) {
            viewBinding.titleContainer.error = "Please Enter Title"
            isValid = false
        } else {
            viewBinding.titleContainer.error = null
        }
        if (viewBinding.desc.text.toString().isNullOrBlank()) {
            viewBinding.descContainer.error = "Please Enter Description"
            isValid = false
        } else {
            viewBinding.descContainer.error = null
        }
        if (viewBinding.date.text.toString().isNullOrBlank()) {
            viewBinding.dateContainer.error = "Please Choose Date"
            isValid = false
        } else {
            viewBinding.dateContainer.error = null
        }
        return isValid

    }

    private fun createTask() {

        if (!valid()) {
            return
        }
        val task = Task(
            title = viewBinding.title.text.toString(),
            description = viewBinding.desc.text.toString(),
            dateTime = calendar.timeInMillis
        )
        TodoDatabase.getInstance(requireContext()).getTodosDao().insertTodo(task)
        onTaskAddedListener?.onTaskAdded()
        dismiss()

    }
    var onTaskAddedListener:OnTaskAddedListener?= null
    fun interface OnTaskAddedListener {
        fun onTaskAdded()
    }
}