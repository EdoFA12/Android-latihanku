package com.edofa.kotlinkalkulator

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.databinding.ActivityMainSharePreferencesBinding

class MainActivitySharePreferences : AppCompatActivity() {
    private val binding : ActivityMainSharePreferencesBinding by lazy { ActivityMainSharePreferencesBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.saveName.setOnClickListener {
            simpanData()
            LoadData()
        }

        LoadData()








    }



    private fun LoadData() {
        val sharePref = getPreferences(Context.MODE_PRIVATE)
        val cookieName = sharePref.getString("username","World")
        binding.name.setText(cookieName)

    }

    private fun simpanData() {
        val sharePref = getPreferences(Context.MODE_PRIVATE)
        val edit = sharePref.edit()
        val userName = binding.setName.text
        edit.putString("username", userName.toString())
        edit.apply()
    }
}