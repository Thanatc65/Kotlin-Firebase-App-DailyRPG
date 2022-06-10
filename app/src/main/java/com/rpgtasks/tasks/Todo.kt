package com.rpgtasks.tasks

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp


data class Todo (
    var name: String = "",
    var description : String = "",
    var timeset : String = "",
    var status : String = "",
    @get:Exclude var id: String = "",

) {
    constructor(todo : Todo) :
            this(todo.name)
}

