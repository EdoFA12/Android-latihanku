package com.edofa.kotlinkalkulator.TransactionIntent

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.MainActivityIntentExplicit
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainTransactionIntentBinding

class MainActivityTransactionIntent : AppCompatActivity() {
    private lateinit var binding : ActivityMainTransactionIntentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTransactionIntentBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.transactionName.setOnClickListener {
            startActivity(Intent(this, MainActivityTransactionFullName::class.java))
        }

        binding.transactionResultCalculator.setOnClickListener {
            startActivity(Intent(this, MainActivityIntentCalculator::class.java))
        }

        binding.transactionExplicit.setOnClickListener {
            startActivity(Intent(this, MainActivityIntentExplicit::class.java))
        }



    }
}