package com.example.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.database.dao.TasksDao
import com.example.todoapp.database.model.Task


@Database(entities = [Task::class], version = 1)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun getTodosDao():TasksDao
    companion object{
    private val Database_name="todos database"
    private var todoDatabaseInstance:TodoDatabase?=null
    fun getInstance(context: Context):TodoDatabase{
        if (todoDatabaseInstance==null)
            todoDatabaseInstance= Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                Database_name)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        return todoDatabaseInstance!!
    }
    }
}