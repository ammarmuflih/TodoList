package com.example.todolist.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todolist.model.todoListModel

class databaseHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object{
        private val DB_NAME = "todo"
        private val DB_VERSION = 1
        private val TABLE_NAME = "todolist"
        private val ID = "id"
        private val TO_DO_TITLE = "todoTitle"
        private val TO_DO_DETAIL = "todoDetail"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $TO_DO_TITLE TEXT, $TO_DO_DETAIL TEXT);"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }

    @SuppressLint("Range")
    fun getAllToDoList(): List<todoListModel>{
        val toDoList = ArrayList<todoListModel>()
        val db = writableDatabase
        val selectQuery = "SELECT *FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null){
            if (cursor.moveToFirst()){
                do {
                    val todo = todoListModel()
                    todo.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    todo.title = cursor.getString(cursor.getColumnIndex(TO_DO_TITLE))
                    todo.detail = cursor.getString(cursor.getColumnIndex(TO_DO_DETAIL))
                    toDoList.add(todo)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return toDoList
    }

    //insert
    fun addToDo(todo: todoListModel):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TO_DO_TITLE, todo.title)
        values.put(TO_DO_DETAIL, todo.detail)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    //select the data of particular id
    @SuppressLint("Range")
    fun getTodo(_id: Int): todoListModel{
        val todo = todoListModel()
        val db = writableDatabase
        val selectQuery = "SELECT *FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        todo.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        todo.title = cursor.getString(cursor.getColumnIndex(TO_DO_TITLE))
        todo.detail = cursor.getString(cursor.getColumnIndex(TO_DO_DETAIL))
        cursor.close()
        return todo
    }

    //delete data by id
    fun deleteTodo(_id: Int):Boolean{
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun updateTodo(todo: todoListModel):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TO_DO_TITLE, todo.title)
        values.put(TO_DO_DETAIL, todo.detail)
        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(todo.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }
}