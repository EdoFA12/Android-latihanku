package com.edofa.kotlinkalkulator.TransactionIntent

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainTransactionFullNameBinding

class MainActivityTransactionFullName : AppCompatActivity() {
    private lateinit var binding : ActivityMainTransactionFullNameBinding
    private var inputFirstNama: String? = null
    private var inputLastName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTransactionFullNameBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnKirimData.setOnClickListener {
            inputFirstNama = binding.inputFirstName.text.toString()
            inputLastName = binding.inputLastName.text.toString()

            val intent = Intent(this, MainActivityReceiveData::class.java)
            intent.putExtra("FirstName", inputFirstNama)
            intent.putExtra("LastName", inputLastName)

            startActivity(intent)


        }

    }
}