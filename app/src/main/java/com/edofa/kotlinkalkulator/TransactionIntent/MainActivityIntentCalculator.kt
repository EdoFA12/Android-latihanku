package com.edofa.kotlinkalkulator.TransactionIntent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainIntentCalculatorBinding

class MainActivityIntentCalculator : AppCompatActivity() {
    private lateinit var binding : ActivityMainIntentCalculatorBinding
//    private var angka1 : Int? = null
//    private var angka2 : Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainIntentCalculatorBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tambah.setOnClickListener {
            try {
                var angka1 = binding.angka1.text.toString().toInt()
                var angka2 = binding.angka2.text.toString().toInt()
                val result: Int = angka1 + angka2
                val intent = Intent(this, MainActivityTransactionResult::class.java)
                intent.putExtra("result", result.toString())
                startActivity(intent)
            }catch (exception:Exception){
                Toast.makeText(this, "Silahkan di isi", Toast.LENGTH_SHORT).show()
            }

        }



    }



}