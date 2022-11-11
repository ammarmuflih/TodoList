package com.example.todolist.adapter

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Contacts
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.addTodo
import com.example.todolist.model.todoListModel

class toDoListAdapter(todoList : List<todoListModel>, internal var context : Context):RecyclerView.Adapter<toDoListAdapter.todoViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
//        this.onItemClickCallback = onItemClickCallback
//    }
    //
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
        //checkbox
        holder.itemView.setOnClickListener{
//            Toast.makeText(holder.itemView.context,"Clicked Task Number"+todoList[position].id, Toast.LENGTH_SHORT).show()
            val isEdit = true
            val DATA_ID = todoList[position].id
            val context=holder.title.context
            val intent = Intent(context, addTodo::class.java).also {
                it.putExtra("IS_EDIT", isEdit)
                it.putExtra("DATA_ID", DATA_ID)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: todoListModel)
    }
}