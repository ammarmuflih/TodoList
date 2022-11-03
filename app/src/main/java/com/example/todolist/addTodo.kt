package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolist.database.databaseHelper
import com.example.todolist.databinding.ActivityAddTodoBinding
import com.example.todolist.model.todoListModel
import java.util.concurrent.BlockingDeque

class addTodo : AppCompatActivity() {

    lateinit var binding: ActivityAddTodoBinding
    var dbHandler: databaseHelper? = null
    var isEditMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHandler = databaseHelper(this)

//        if (intent != null && intent.getStringExtra("Mode") == "E") {
//            //Update data
//        } else {
//            //Insert new data
//        }
//
//        binding.saveToDoButton.setOnClickListener {
//            if (isEditMode) {
//                //Update
//            } else {
//                //Insert
//                binding.saveToDoButton.setOnClickListener { insertTodo() }
//            }
//        }
        binding.saveToDoButton.setOnClickListener { insertTodo() }
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
}

