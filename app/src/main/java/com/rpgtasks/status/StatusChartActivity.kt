package com.rpgtasks.status

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rpgtasks.FakeAuth
import com.rpgtasks.FirestoreSchema
import com.rpgtasks.databinding.ActivityStatusChartBinding
import com.rpgtasks.tasks.TodoProgress
import com.rpgtasks.tasks.TodoStatusPoint


class StatusChartActivity : AppCompatActivity() {

    private val binding by lazy { ActivityStatusChartBinding.inflate(layoutInflater) }

    private var chart: RadarChart? = null

    var labels = arrayOf("STR", "INT", "AGI", "LUK","WIS")

    var str = 1
    var int = 1
    var agi = 1
    var luk = 1
    var wis = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "RadarChartActivity"

        chart = binding.chart1
        chart!!.setBackgroundColor(Color.rgb(60, 65, 82))

        chart!!.description.isEnabled = false

        chart!!.webLineWidth = 1f
        chart!!.webColor = Color.LTGRAY
        chart!!.webLineWidthInner = 1f
        chart!!.webColorInner = Color.LTGRAY
        chart!!.webAlpha = 100

        setData(TodoStatusPoint())

        chart!!.animateXY(1400, 1400, Easing.EaseInOutQuad)

        val xAxis = chart!!.xAxis
        xAxis.textSize = 9f
        xAxis.yOffset = 0f
        xAxis.xOffset = 0f
        xAxis.textColor = Color.WHITE

        val yAxis = chart!!.yAxis
        yAxis.setLabelCount(5, true)
        yAxis.textSize = 9f
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 100f
        yAxis.setDrawLabels(false)

        val l = chart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 5f
        l.textColor = Color.WHITE

        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        Firebase.firestore
            .collection("userId")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection(FirestoreSchema.CPOINT)
            .document(FakeAuth.currentUser.id)
            .addSnapshotListener { snapshot, e ->
                snapshot?.toObject<TodoStatusPoint>()?.also { setData(it) }
            }

    }

    private fun setData(todo: TodoStatusPoint) {

        str = todo.progressstr
        int = todo.progressint
        agi = todo.progressagi
        luk = todo.progressluk
        wis = todo.progresswis

        val entries1: ArrayList<RadarEntry> = ArrayList()

        entries1.add(RadarEntry(str.toFloat()))
        entries1.add(RadarEntry(int.toFloat()))
        entries1.add(RadarEntry(agi.toFloat()))
        entries1.add(RadarEntry(luk.toFloat()))
        entries1.add(RadarEntry(wis.toFloat()))

        val set1 = RadarDataSet(entries1, "Status Chart")
        set1.color = Color.rgb(103, 110, 129)
        set1.fillColor = Color.rgb(103, 110, 129)
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)

        val sets: ArrayList<IRadarDataSet> = ArrayList()
        sets.add(set1)

        val data = RadarData(sets)

        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)
        chart!!.data = data
        chart!!.invalidate()
    }

}