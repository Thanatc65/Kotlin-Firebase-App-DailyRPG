package com.rpgtasks.tasks

import android.content.DialogInterface
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rpgtasks.R

class TaskViewModel : ViewModel() {

    class TodoChangeEvent

    private val _events = MutableLiveData<TodoChangeEvent>()
    val events: LiveData<TodoChangeEvent> = _events

    fun emitEvent() {
        this._events.value = TodoChangeEvent()
    }
}