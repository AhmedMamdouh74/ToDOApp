package com.example.todoapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "iD")
    val id:Int?=null,
    @ColumnInfo
    var title:String?=null,
    @ColumnInfo
    var description:String?=null,
    @ColumnInfo
    var isDone:Boolean?=null,
    @ColumnInfo
    val dateTime:Long?=null
):java.io.Serializable
