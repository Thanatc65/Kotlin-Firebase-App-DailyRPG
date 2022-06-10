package com.rpgtasks.showfinish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.databinding.FragmentShowfinishBinding
import com.rpgtasks.tasks.Todo

class ShowFinishFragment : Fragment() {

    // Layout & VM
    private var binding: FragmentShowfinishBinding? = null

    // Data
    private val db = FirebaseFirestore.getInstance()
    private var showFinishFragmentAdapter: ShowFinishFragmentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View =
        with(FragmentShowfinishBinding.inflate(inflater, parent, false)) {
            // Keep binding
            binding = this

            // Setup UI

            val query = db.collection("userId")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection(FirestoreSchema.TODOTASK).whereEqualTo("status", "DELETE")
            val options = FirestoreRecyclerOptions.Builder<Todo>()
                .setQuery(query) {it.toObject(Todo::class.java)!!.apply { id = it.id }}
                .build()

            showFinishFragmentAdapter = ShowFinishFragmentAdapter(options)
            alredyDeleteTasks.adapter = showFinishFragmentAdapter

            // Return view
            root
        }

    override fun onStart() {
        super.onStart()
        showFinishFragmentAdapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        showFinishFragmentAdapter?.stopListening()
    }
}