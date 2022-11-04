package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapter.toDoListAdapter
import com.example.todolist.database.databaseHelper
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.todoListModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recycler_todo : RecyclerView
    var todolistAdapter : toDoListAdapter ?= null
    var dbhandler : databaseHelper ?= null
    var todoList : List<todoListModel> = ArrayList<todoListModel>()
    var linearlayoutManager : LinearLayoutManager ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTodoButton.setOnClickListener{ toAddTodoActivity()}

        dbhandler = databaseHelper(this)
        recycler_todo = findViewById(R.id.recyclerViewList)
        fetchList()
    }

    private fun toAddTodoActivity(){
        intent = Intent(this, addTodo::class.java)
        startActivity(intent)
    }

    private fun fetchList(){
        todoList = dbhandler!!.getAllToDoList()
        todolistAdapter = toDoListAdapter(todoList, applicationContext)
        linearlayoutManager = LinearLayoutManager(applicationContext)
        recycler_todo.layoutManager = linearlayoutManager
        recycler_todo.adapter = todolistAdapter
        todolistAdapter?.notifyDataSetChanged()
    }

    //pushtest

}

