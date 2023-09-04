package com.example.todoapp.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentSettingsBinding
import com.example.todoapp.ui.home.AddTaskFragment
import com.example.todoapp.ui.home.fragments.tasks_List.FragmentList

class FragmentSettings : Fragment() {
    lateinit var viewBinding: FragmentSettingsBinding
    var fragmentList = FragmentList()
    var addTaskFragment = AddTaskFragment()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}