package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.adapter.toDoListAdapter
import com.example.todolist.database.databaseHelper
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.todoListModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var adapter : toDoListAdapter ?= null
    var dbhandler : databaseHelper ?= null
    var todoList : List<todoListModel> = ArrayList<todoListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTodoButton.setOnClickListener{ toAddTodoActivity()}

        dbhandler = databaseHelper(this)
    }

    private fun toAddTodoActivity(){
        intent = Intent(this, addTodo::class.java)
        startActivity(intent)
    }

    private fun fetchList(){
        todoList = dbhandler!!.getAllToDoList()
    }

    private fun testPush(){
        //code here
    }
}

