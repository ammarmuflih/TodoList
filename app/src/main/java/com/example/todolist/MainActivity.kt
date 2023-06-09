package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    var title = "To Do List"
    var isEdit : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setActionBarTitle(title)
        setContentView(binding.root)

        val itemAdapter = todolistAdapter
        itemAdapter?.setOnItemClickCallback(this)

        binding.addTodoButton.setOnClickListener{ toAddTodoActivity()}

        dbhandler = databaseHelper(this)
        recycler_todo = findViewById(R.id.recyclerViewList)
        fetchList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.resetAll){
            deleteallTodo()
            Toast.makeText(this,"Action one selected", Toast.LENGTH_SHORT).show()
            return true
        }
        if(id == R.id.action_two){
            Toast.makeText(this,"Action two selected", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toAddTodoActivity(){
        intent = Intent(this, todoController::class.java)
        startActivity(intent)
        finish()
    }

    private fun fetchList(){
        todoList = dbhandler!!.getAllToDoList()
        todolistAdapter = toDoListAdapter(todoList, applicationContext)
        linearlayoutManager = LinearLayoutManager(applicationContext)
        recycler_todo.layoutManager = linearlayoutManager
        recycler_todo.adapter = todolistAdapter
        todolistAdapter?.notifyDataSetChanged()

//        todolistAdapter!!.setOnItemClickCallback(object : toDoListAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: todoListModel) {
//                showSelected()
//            }
//        })
    }

//    private fun showSelected(){
//        Toast.makeText(this,"TerClick", Toast.LENGTH_SHORT).show()
//    }

    private fun setActionBarTitle(title: String){
        supportActionBar?.title = title
    }

    private fun deleteallTodo(){
        dbhandler?.reset()
        fetchList()
    }

    override fun onItemClick()
}

