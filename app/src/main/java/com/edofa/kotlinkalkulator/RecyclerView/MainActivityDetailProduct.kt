package com.edofa.kotlinkalkulator.RecyclerView

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainDetailProductBinding

class MainActivityDetailProduct : AppCompatActivity() {
    private lateinit var binding : ActivityMainDetailProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainDetailProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nama = intent.getStringExtra("Namakey")
        val harga = intent.getStringExtra("Hargakey")
        val image = intent.getStringExtra("Imagekey")
        val description = intent.getStringExtra("Descriptionkey")

        binding.tvName.setText(nama)
        binding.tvPrice.setText("Rp. $harga")
        binding.tvDescription.setText(description)
        Glide.with(this).load(image).into(binding.imageTitle)

    }

}