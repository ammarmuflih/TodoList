package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityAddTodoBinding

class addTodo : AppCompatActivity() {

    lateinit var binding: ActivityAddTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveToDoButton.setOnClickListener { saveTodo() }

    }

    private fun saveTodo(){
        
    }
}