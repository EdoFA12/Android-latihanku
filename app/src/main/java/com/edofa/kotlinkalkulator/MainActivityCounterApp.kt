package com.edofa.kotlinkalkulator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.databinding.ActivityCounterAppBinding

class MainActivityCounterApp : AppCompatActivity() {
    private val binding:  ActivityCounterAppBinding by lazy {ActivityCounterAppBinding.inflate(layoutInflater)}
    private var angka: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        binding.kurang.setOnClickListener {
            if (angka > 0){
                angka--
                binding.angka.text = angka.toString()
            }else{
                Toast.makeText(this, "sudah tidak bisa diKurangi", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tambah.setOnClickListener {
            ++angka
            binding.angka.text = angka.toString()
        }
    }

}