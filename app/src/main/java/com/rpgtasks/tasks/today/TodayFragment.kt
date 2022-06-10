package com.rpgtasks.tasks.today

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.rpgtasks.CreateTodoActivity
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.MainActivity
import com.rpgtasks.R
import com.rpgtasks.databinding.FragmentTodayBinding
import com.rpgtasks.tasks.TaskViewModel
import com.rpgtasks.tasks.Todo
import com.rpgtasks.tasks.Todotask

class TodayFragment : Fragment() {

    // Layout & VM
    private var binding: FragmentTodayBinding? = null
    private val vm: TaskViewModel by activityViewModels()

    // Data
    private val db = FirebaseFirestore.getInstance()
    private var todayFragmentAdapter: TodayFragmentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View =
        with(FragmentTodayBinding.inflate(inflater, parent, false)) {
            // Keep binding
            binding = this

            // Setup UI

            gotocreateButton.setOnClickListener {
                startActivity(Intent(context, CreateTodoActivity::class.java))
            }

            val query = db.collection("userId").document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection(FirestoreSchema.TODOTASK).whereNotEqualTo("status", "DELETE")
            val options = FirestoreRecyclerOptions.Builder<Todo>()
                .setQuery(query) {it.toObject(Todo::class.java)!!.apply { id = it.id }}
                .build()

            todayFragmentAdapter = TodayFragmentAdapter(options) {
                vm.emitEvent()
            }
            todayTasks.adapter = todayFragmentAdapter

            // Return view
            root
        }

    override fun onStart() {
        super.onStart()
        todayFragmentAdapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        todayFragmentAdapter?.stopListening()
    }
}


