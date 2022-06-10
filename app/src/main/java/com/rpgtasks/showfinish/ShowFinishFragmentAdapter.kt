package com.rpgtasks.showfinish

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.rpgtasks.databinding.ItemAlreadyDeleteBinding
import com.rpgtasks.tasks.Todo

class ShowFinishFragmentAdapter(options: FirestoreRecyclerOptions<Todo>)
    : FirestoreRecyclerAdapter<Todo, ShowFinishFragmentViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowFinishFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAlreadyDeleteBinding.inflate(inflater, parent, false)
        return ShowFinishFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowFinishFragmentViewHolder, position: Int, model: Todo) {
        holder.bind(model)
    }


}