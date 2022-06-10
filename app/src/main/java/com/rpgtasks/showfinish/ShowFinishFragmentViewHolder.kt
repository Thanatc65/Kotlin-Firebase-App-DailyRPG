package com.rpgtasks.showfinish

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rpgtasks.databinding.ItemAlreadyDeleteBinding
import com.rpgtasks.tasks.Todo

class ShowFinishFragmentViewHolder(
    private val binding: ItemAlreadyDeleteBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val db = FirebaseFirestore.getInstance()

    fun bind(todo : Todo) = with(binding) {
        textViewName.text = todo.name
        textViewDescripton.text = todo.description
        setTime.text = todo.timeset
        deleteButton.setOnClickListener {

            val builder = AlertDialog.Builder(binding.root.context)
            builder.setMessage("Are you sure to Delete")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection("todotask")
                    .document(todo.id)
                    .delete()

                dialog.cancel()
            })

            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

            val alert = builder.create()
            alert.show()
        }

        reDoTask.setOnClickListener {

            val builder = AlertDialog.Builder(binding.root.context)
            builder.setMessage("Are you sure to Re do task")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection("todotask")
                    .document(todo.id)
                    .update("status","")

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