package com.rpgtasks.tasks.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.rpgtasks.databinding.ActivityMainBinding
import com.rpgtasks.databinding.ItemTaskBinding
import com.rpgtasks.tasks.Todo

class TodayFragmentAdapter(
    options: FirestoreRecyclerOptions<Todo>,
    private val eventStatusChangedHandler: EventStatusChangedHandler
) : FirestoreRecyclerAdapter<Todo, TodayFragmentViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TodayFragmentViewHolder(binding, eventStatusChangedHandler)
    }

    override fun onBindViewHolder(holder: TodayFragmentViewHolder, position: Int, model: Todo) =
        holder.bind(model)

}




