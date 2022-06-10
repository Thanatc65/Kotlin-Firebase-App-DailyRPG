package com.rpgtasks

import android.R
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rpgtasks.databinding.ActivitySettingBinding
import java.util.*


class SettingActivity : AppCompatActivity() {

    val languages = arrayOf("Language", "English", "Thai")

    private val binding by lazy { ActivitySettingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonChangeTheme.setOnClickListener { chooseThemeDialog() }

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerlan.adapter = adapter
        binding.spinnerlan.setSelection(0)
        binding.spinnerlan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                val selectedLang = adapterView.getItemAtPosition(i).toString()
                if (selectedLang == "English") {
                    setLocal(this@SettingActivity, "en")
                    val intent = Intent(this@SettingActivity, MainActivity::class.java)
                    startActivity(intent)
                } else if (selectedLang == "Thai") {
                    setLocal(this@SettingActivity, "th")
                    val intent = Intent(this@SettingActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }
    }

    private fun chooseThemeDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Theme")
        val styles = arrayOf("Light","Dark")
        val checkedItem = 0

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()

                    dialog.dismiss()
                }

            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    fun setLocal(activity: Activity, langCode: String?) {
        val locale = Locale(langCode)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}