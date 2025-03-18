package com.edofa.kotlinkalkulator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.databinding.ActivityMainIntentExplicitBinding

class MainActivityIntentExplicit : AppCompatActivity() {
    private val binding : ActivityMainIntentExplicitBinding by lazy {ActivityMainIntentExplicitBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnStart.setOnClickListener {
            search()

        }

        binding.btnStartNumber.setOnClickListener {
            receiveNumber()
        }


    }






    private fun receiveNumber() {
        val inputNumber =  binding.edtNumber.text.toString().toInt()
        val numberIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $inputNumber"))
        startActivity(numberIntent)


    }



    private fun search() {
        val url = binding.edtUrl.text.toString()
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url.com"))
        startActivity(urlIntent)
    }
}