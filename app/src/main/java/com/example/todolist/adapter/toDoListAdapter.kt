package com.example.todolist.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.model.todoListModel
import com.example.todolist.todoController

class toDoListAdapter(todoList : List<todoListModel>, var context : Context):RecyclerView.Adapter<toDoListAdapter.todoViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: MainActivity){
        this.onItemClickCallback = onItemClickCallback
    }

    internal var todoList : List<todoListModel> = ArrayList()
    init {
        this.todoList = todoList
    }

    inner class todoViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.doTitle)
        var detail = view.findViewById<TextView>(R.id.doDetail)
        var checkBox = view.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return todoViewHolder(view)
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.title.text = todo.title
        holder.detail.text = todo.detail
        var checkBoxStateArray = SparseBooleanArray()
        //checkbox
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"Clicked Task Number"+todoList[position].id, Toast.LENGTH_SHORT).show()
            updateSelected(todoList[position].id, context)

        }

        holder.checkBox.setOnClickListener {
            if (!checkBoxStateArray.get(position, false)){
                holder.checkBox.isChecked = true
                checkBoxStateArray.put(position, true)
                Toast.makeText(holder.checkBox.context, "Checked"+todoList[position].id, Toast.LENGTH_SHORT).show()
//                deleteSelected(todoList[position].id, holder.title.context)
            }else{
                holder.checkBox.isChecked = false
                checkBoxStateArray.put(position, false)
                Toast.makeText(holder.checkBox.context, "unChecked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: todoListModel)
    }

//    private fun deleteSelected(id: Int, context: Context){
//        lateinit var recycler_todo : RecyclerView
//        var dbhandler : databaseHelper ?= null
//        var todolistAdapter : toDoListAdapter ?= null
//        var linearlayoutManager : LinearLayoutManager?= null
//        var success : Boolean = false
//        val todo : todoListModel = todoListModel()
//        dbhandler?.deleteTodo(id)
//
////        todoList = dbhandler!!.getAllToDoList()
////        todolistAdapter = toDoListAdapter(todoList, context)
////        linearlayoutManager = LinearLayoutManager(context)
////        recycler_todo.layoutManager = linearlayoutManager
////        recycler_todo.adapter = todolistAdapter
////        todolistAdapter?.notifyDataSetChanged()
//
////        if (success){
////            todoList = dbhandler!!.getAllToDoList()
////            todolistAdapter = toDoListAdapter(todoList, context)
////            linearlayoutManager = LinearLayoutManager(context)
////            recycler_todo.layoutManager = linearlayoutManager
////            recycler_todo.adapter = todolistAdapter
////            todolistAdapter?.notifyDataSetChanged()
////        }else{
////            Toast.makeText(context,"Someting wrong", Toast.LENGTH_SHORT).show()
////        }
//    }

    private fun updateSelected(id: Int, context: Context){
        val isEdit = true
        val DATA_ID = id
        val intent = Intent(context, todoController::class.java).also {
            it.putExtra("IS_EDIT", isEdit)
            it.putExtra("DATA_ID", DATA_ID)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
//        (context as Activity).finish()
    }
}