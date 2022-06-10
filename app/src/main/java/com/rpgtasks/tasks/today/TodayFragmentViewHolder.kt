package com.rpgtasks.tasks.today

import android.content.DialogInterface
import android.provider.Settings.Global.getString
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.MainActivity
import com.rpgtasks.R
import com.rpgtasks.databinding.ActivityMainBinding
import com.rpgtasks.databinding.FragmentTodayBinding
import com.rpgtasks.databinding.ItemTaskBinding
import com.rpgtasks.tasks.Todo


typealias EventStatusChangedHandler = () -> Unit

class TodayFragmentViewHolder(
    private val binding: ItemTaskBinding,
    private val eventStatusChangedHandler: EventStatusChangedHandler
) : RecyclerView.ViewHolder(binding.root) {

    private val db = FirebaseFirestore.getInstance()

    fun bind(todo : Todo) = with(binding) {
        textViewName.text = todo.name
        textViewDescripton.text = todo.description
        setTime.text = todo.timeset

        deleteButton.setOnClickListener {

            val builder = AlertDialog.Builder(binding.root.context)
            builder.setMessage("Are you sure to Finish task")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->

                eventStatusChangedHandler()

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection("todotask")
                    .document(todo.id)
                    .update("status","DELETE")

                dialog.cancel()
            })

            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

            val alert = builder.create()
            alert.show()
        }
    }

}