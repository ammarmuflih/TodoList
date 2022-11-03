package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.todoListModel

class toDoListAdapter(todoList : List<todoListModel>, internal var context : Context):RecyclerView.Adapter<toDoListAdapter.todoViewHolder>() {

    //Item Click Listener
    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    //
    internal var todoList : List<todoListModel> = ArrayList()
    init {
        this.todoList = todoList
    }

    inner class todoViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.doTitle)
        var detail = view.findViewById<TextView>(R.id.doDetail)
        var checkBox = view.findViewById<CheckBox>(R.id.checkBox)

//        init{
//            itemView.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//        }
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
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}