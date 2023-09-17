package com.example.todoapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.ui.home.fragments.tasks_List.FragmentList
import com.example.todoapp.ui.home.fragments.FragmentSettings
import com.example.todoapp.ui.home.fragments.tasks_List.AdapterList
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    var fragmentListRef: FragmentList? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.bottomNavigation.setOnItemSelectedListener {
            fragmentListRef = FragmentList()
            if (it.itemId == R.id.navigatoin_list) {
                showFragment(fragmentListRef!!)
            } else if (it.itemId == R.id.navigation_setting) {
                showFragment(FragmentSettings())
            }

            return@setOnItemSelectedListener true
        }
        viewBinding.floatingActionAddTask.setOnClickListener {
            showAddTaskBottomSheet()
        }
        viewBinding.bottomNavigation.selectedItemId = R.id.navigatoin_list
    }

    private fun showAddTaskBottomSheet() {
        var addTaskSheet = AddTaskFragment()
        addTaskSheet.onTaskAddedListener = AddTaskFragment.OnTaskAddedListener {
            Snackbar.make(
                viewBinding.root, "Task Added Successfully", Snackbar.LENGTH_LONG
            ).show()

            fragmentListRef?.loadTasksFromDataBase()

        }
        addTaskSheet.show(supportFragmentManager, "")
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .commit()


    }

}