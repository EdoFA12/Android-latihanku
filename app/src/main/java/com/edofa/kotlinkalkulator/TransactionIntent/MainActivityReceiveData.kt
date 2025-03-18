package com.edofa.kotlinkalkulator.TransactionIntent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainReceiveDataBinding

class MainActivityReceiveData : AppCompatActivity() {
    private lateinit var binding : ActivityMainReceiveDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainReceiveDataBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val firstName : String = intent.getStringExtra("FirstName").toString()
        val lastName : String =  intent.getStringExtra("LastName").toString()
        binding.firstName.setText(firstName)
        binding.lastName.setText(lastName)

    }
}