package com.example.todoapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.database.model.Task
import java.util.Date


@Dao
interface TasksDao {
    @Insert
    fun insertTodo(task: Task)

    @Delete
    fun deleteTodo(task: Task)

    @Update
    fun updateTodo(task: Task)

    @Query("SELECT *FROM tasks ")
    fun getAllTasks(): List<Task>

    @Query("SELECT *FROM tasks where dateTime=:dateTime ")
    fun getTasksByDate(dateTime:Long): List<Task>

}