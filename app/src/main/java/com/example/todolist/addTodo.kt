package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todolist.database.databaseHelper
import com.example.todolist.databinding.ActivityAddTodoBinding
import com.example.todolist.model.todoListModel

class addTodo : AppCompatActivity() {

    lateinit var binding: ActivityAddTodoBinding
    var dbHandler: databaseHelper? = null
    var isEdit: Boolean = false
    var data_id: Int = 0
    var title = "Add Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isEdit = intent.getBooleanExtra("IS_EDIT", isEdit)
        data_id = intent.getIntExtra("DATA_ID", data_id)
        dbHandler = databaseHelper(this)
        if(isEdit){
            title = "Edit"
            setActionBarTitle(title)
            val todo = dbHandler!!.getTodo(data_id)
            binding.titleEditText.setText(todo.title)
            binding.detailEditText.setText(todo.detail)
        }else{
            setActionBarTitle(title)
            binding.saveToDoButton.setOnClickListener { insertTodo() }
        }

        if(isEdit){
            binding.saveToDoButton.setOnClickListener { updateTodo(data_id) }
        }
//        binding.saveToDoButton.setOnClickListener { insertTodo() }
    }

    private fun insertTodo(){
        var success : Boolean = false
        val todo : todoListModel = todoListModel()
        todo.title = binding.titleEditText.text.toString()
        todo.detail = binding.detailEditText.text.toString()

        success = dbHandler?.addToDo(todo) as Boolean

        if (success){
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(applicationContext, "Something went Wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setActionBarTitle(title: String){
        supportActionBar?.title = title
    }

    private fun updateTodo(id: Int){
        var success : Boolean = false
        val todo : todoListModel = todoListModel()
        todo.id = intent.getIntExtra("DATA_ID", 0)
        todo.title = binding.titleEditText.text.toString()
        todo.detail = binding.detailEditText.text.toString()

        success = dbHandler?.updateTodo(todo) as Boolean

        if(success){
            val i = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(applicationContext,"Something when wrong",Toast.LENGTH_SHORT).show()
        }
    }
}

