package com.example.todoapp.ui.edit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.todoapp.constance.constance
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.ActivityEditBinding
import com.example.todoapp.ui.home.HomeActivity
import com.example.todoapp.ui.home.fragments.tasks_List.FragmentList
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar

class EditActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityEditBinding
    lateinit var task: Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.back.setOnClickListener {

            startActivity(Intent(this, HomeActivity::class.java))
        }
        task = (((intent.getSerializableExtra(constance.task_key) as? Task)!!))
        showData(task)
        viewBinding.saveBtn.setOnClickListener(View.OnClickListener {
            updateTodo()

        })
    }

    private fun showData(task: Task) {
        viewBinding.title.setText(task.title)
        viewBinding.desc.setText(task.description)
        val date = converLongToTime(task.dateTime)
        viewBinding.date.text = date
    }


    private fun converLongToTime(date: Long?): String {
        val date = Date(date!!)
        val format = SimpleDateFormat("dd /MM / yyyy")
        return format.format(date)
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

        return isValid

    }
    fun updateTodo() {
        if (valid() == false) {
            return
        }
        task.title = viewBinding.titleContainer.editText?.text.toString()
        task.description = viewBinding.descContainer.editText?.text.toString()
        TodoDatabase.getInstance(this).getTodosDao().updateTodo(task)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}