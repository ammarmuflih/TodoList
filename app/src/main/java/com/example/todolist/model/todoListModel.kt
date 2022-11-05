package com.example.todolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//class todoListModel {
//    var id: Int = 0
//    var title: String = ""
//    var detail: String = ""
//}

@Parcelize
data class todoListModel(
    var id: Int = 0,
    var title: String = "",
    var detail: String = ""
) : Parcelable