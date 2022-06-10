package com.rpgtasks.status

import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rpgtasks.*
import com.rpgtasks.advertise.AdvertiseActivity
import com.rpgtasks.databinding.FragmentStatusBinding
import com.rpgtasks.tasks.TodoCpoint
import com.rpgtasks.tasks.TodoStatusPoint

class StatusFragment : Fragment() {

    private var binding: FragmentStatusBinding? = null
    private val db = FirebaseFirestore.getInstance()

    //point and status
    var cpoint = 0
    var progressSTR = 1
    var progressINT = 1
    var progressAGI = 1
    var progressLUK = 1
    var progressWIS = 1

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View =
        with(FragmentStatusBinding.inflate(inflater, parent, false)) {
            // Keep binding
            binding = this

            //Color progressbar
            progressBarStr.progressTintList = ColorStateList.valueOf(Color.RED)
            progressBarInt.progressTintList = ColorStateList.valueOf(Color.BLUE)
            progressBarAgi.progressTintList = ColorStateList.valueOf(Color.GREEN)
            progressBarLuk.progressTintList = ColorStateList.valueOf(Color.YELLOW)
            progressBarWis.progressTintList = ColorStateList.valueOf(Color.LTGRAY)

            updateUistatuspoint(TodoStatusPoint())
            updateUicpoint(TodoCpoint())

            statusChartButton.setOnClickListener {

                startActivity(Intent(context,StatusChartActivity::class.java))

            }

            adCpoint.setOnClickListener {

                startActivity(Intent(context,AdvertiseActivity::class.java))
                cpoint += 1
                val todo = TodoCpoint(
                    cpoint
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINTS)
                    .document(FakeAuth.currentUser.id)
                    .set(todo, SetOptions.merge())


            }

            addSTR.setOnClickListener {
                if(cpoint >= 1){
                    if (progressSTR < 100){
                    progressBarStr.progress = progressSTR
                    progressSTR += 1
                    statusSTR.text = "$progressSTR"
                    cpoint -= 1
                    characterPoint.text = "$cpoint"
                    }
                    if (progressSTR == 100){
                        AlertDialog.Builder(binding!!.root.context)
                            .setMessage("You reach max status")
                            .create().show()
                    }
                }

                if (cpoint <= 0){

                }

                val todocpoint = TodoStatusPoint(
                    progressSTR,
                    progressINT,
                    progressAGI,
                    progressLUK,
                    progressWIS
                )
                val todo = TodoCpoint(
                    cpoint
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINT)
                    .document(FakeAuth.currentUser.id)
                    .set(todocpoint, SetOptions.merge())

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINTS)
                    .document(FakeAuth.currentUser.id)
                    .set(todo, SetOptions.merge())

            }

            addINT.setOnClickListener {
                if(cpoint >= 1){
                    if (progressINT < 100){
                        progressBarInt.progress = progressINT
                        progressINT += 1
                        statusINT.text = "$progressINT"
                        cpoint -= 1
                        characterPoint.text = "$cpoint"
                    }
                    if (progressINT == 100){
                        AlertDialog.Builder(binding!!.root.context)
                            .setMessage("You reach max status")
                            .create().show()
                    }
                }

                if (cpoint <= 0){

                }

                val todocpoint = TodoStatusPoint(
                    progressSTR,
                    progressINT,
                    progressAGI,
                    progressLUK,
                    progressWIS
                )
                val todo = TodoCpoint(
                    cpoint
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINT)
                    .document(FakeAuth.currentUser.id)
                    .set(todocpoint, SetOptions.merge())

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINTS)
                    .document(FakeAuth.currentUser.id)
                    .set(todo, SetOptions.merge())

            }

            addAGI.setOnClickListener {
                if(cpoint >= 1){
                    if (progressAGI < 100){
                        progressBarAgi.progress = progressAGI
                        progressAGI += 1
                        statusAGI.text = "$progressAGI"
                        cpoint -= 1
                        characterPoint.text = "$cpoint"
                    }
                    if (progressAGI == 100){
                        AlertDialog.Builder(binding!!.root.context)
                            .setMessage("You reach max status")
                            .create().show()
                    }
                }

                if (cpoint <= 0){

                }

                val todocpoint = TodoStatusPoint(
                    progressSTR,
                    progressINT,
                    progressAGI,
                    progressLUK,
                    progressWIS
                )
                val todo = TodoCpoint(
                    cpoint
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINT)
                    .document(FakeAuth.currentUser.id)
                    .set(todocpoint, SetOptions.merge())

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINTS)
                    .document(FakeAuth.currentUser.id)
                    .set(todo, SetOptions.merge())

            }

            addLUK.setOnClickListener {
                if(cpoint >= 1){
                    if (progressLUK < 100){
                        progressBarLuk.progress = progressLUK
                        progressLUK += 1
                        statusLUK.text = "$progressLUK"
                        cpoint -= 1
                        characterPoint.text = "$cpoint"
                    }
                    if (progressLUK == 100){
                        AlertDialog.Builder(binding!!.root.context)
                            .setMessage("You reach max status")
                            .create().show()
                    }
                }

                if (cpoint <= 0){

                }

                val todocpoint = TodoStatusPoint(
                    progressSTR,
                    progressINT,
                    progressAGI,
                    progressLUK,
                    progressWIS
                )
                val todo = TodoCpoint(
                    cpoint
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINT)
                    .document(FakeAuth.currentUser.id)
                    .set(todocpoint, SetOptions.merge())

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINTS)
                    .document(FakeAuth.currentUser.id)
                    .set(todo, SetOptions.merge())

            }

            addWIS.setOnClickListener {
                if(cpoint >= 1){
                    if (progressWIS < 100){
                        progressBarWis.progress = progressWIS
                        progressWIS += 1
                        statusWIS.text = "$progressWIS"
                        cpoint -= 1
                        characterPoint.text = "$cpoint"
                    }
                    if (progressWIS == 100){
                        AlertDialog.Builder(binding!!.root.context)
                            .setMessage("You reach max status")
                            .create().show()
                    }
                }

                if (cpoint <= 0){

                }

                val todocpoint = TodoStatusPoint(
                    progressSTR,
                    progressINT,
                    progressAGI,
                    progressLUK,
                    progressWIS
                )
                val todo = TodoCpoint(
                    cpoint
                )

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINT)
                    .document(FakeAuth.currentUser.id)
                    .set(todocpoint, SetOptions.merge())

                db.collection("userId")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .collection(FirestoreSchema.CPOINTS)
                    .document(FakeAuth.currentUser.id)
                    .set(todo, SetOptions.merge())

            }

            Firebase.firestore
                .collection("userId")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection(FirestoreSchema.CPOINT)
                .document(FakeAuth.currentUser.id)
                .addSnapshotListener { snapshot, e ->
                    snapshot?.toObject<TodoStatusPoint>()?.also { updateUistatuspoint(it) }
                }

            Firebase.firestore
                .collection("userId")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection(FirestoreSchema.CPOINTS)
                .document(FakeAuth.currentUser.id)
                .addSnapshotListener { snapshot, e ->
                    snapshot?.toObject<TodoCpoint>()?.also { updateCpoint(it) }
                }


            root
        }

    private fun updateUistatuspoint(todoprogress: TodoStatusPoint){

        // bind Text and Progressbar STR
        progressSTR = todoprogress.progressstr
        binding?.statusSTR?.text = progressSTR.toString()
        binding?.progressBarStr?.progress = progressSTR

        // bind Text and Progressbar INT
        progressINT = todoprogress.progressint
        binding?.statusINT?.text = progressINT.toString()
        binding?.progressBarInt?.progress = progressINT

        // bind Text and Progressbar AGI
        progressAGI = todoprogress.progressagi
        binding?.statusAGI?.text = progressAGI.toString()
        binding?.progressBarAgi?.progress = progressAGI

        // bind Text and Progressbar LUK
        progressLUK = todoprogress.progressluk
        binding?.statusLUK?.text = progressLUK.toString()
        binding?.progressBarLuk?.progress = progressLUK

        progressWIS = todoprogress.progresswis
        binding?.statusWIS?.text = progressWIS.toString()
        binding?.progressBarWis?.progress = progressWIS

    }

    private fun updateCpoint(todo : TodoCpoint){

        db.collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.CPOINTS)
            .document(FakeAuth.currentUser.id)
            .set(todo, SetOptions.merge())

        updateUicpoint(todo)
    }

    private fun updateUicpoint(todo : TodoCpoint){

        // bind CharacterPoint
        cpoint = todo.cpoint
        binding?.characterPoint?.text = cpoint.toString()

    }

}