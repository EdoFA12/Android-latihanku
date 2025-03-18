package com.edofa.kotlinkalkulator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.databinding.ActivityMainKalkulatorBinding

class MainActivityKalkulator : AppCompatActivity() {
    private val binding : ActivityMainKalkulatorBinding by lazy { ActivityMainKalkulatorBinding.inflate(layoutInflater) }

    private var numberOne : Int ? = null
    private var numberTwo : Int ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        binding.Perkalian.setOnClickListener {
            checkNumberInput()
            if (validasi(numberOne, numberTwo)) {
//                result(numberOne, numberTwo, "*")
                perkalian(numberOne,numberTwo)
            }

        }

        binding.penjumlahan.setOnClickListener {
            checkNumberInput()
            if (validasi(numberOne, numberTwo)) {
//                result(numberOne, numberTwo, "+")
                penjumlahan(numberOne,numberTwo)
            }
        }

        binding.pengurangan.setOnClickListener {
            checkNumberInput()
            if (validasi(numberOne, numberTwo)) {
//                result(numberOne, numberTwo, "-")
                pengurangan(numberOne,numberTwo)
            }
        }

        binding.pembagian.setOnClickListener {
            checkNumberInput()
            if (validasi(numberOne, numberTwo)) {
//                result(numberOne, numberTwo, ":")
                pembagian(numberOne,numberTwo)
            }
        }

        binding.reset.setOnClickListener {
            binding.hasilOperasi.setText("Hasil Operasi")
        }
    }

    private fun pembagian(numberOne: Int?, numberTwo: Int?) {
        val result = numberOne!! / numberTwo!!
        binding.hasilOperasi.setText(result.toString())
    }

    private fun pengurangan(numberOne: Int?, numberTwo: Int?) {
        val result = numberOne!! - numberTwo!!
        binding.hasilOperasi.setText(result.toString())
    }

    private fun perkalian(numberOne: Int?, numberTwo: Int?) {
        val result = numberOne!! * numberTwo!!
        binding.hasilOperasi.setText(result.toString())
    }

    private fun penjumlahan(numberOne: Int?, numberTwo: Int?) {
        val result = numberOne!! + numberTwo!!
        binding.hasilOperasi.setText(result.toString())
    }


    private fun checkNumberInput() {
        numberOne = binding.angka1.text.toString().trim().toIntOrNull()
        numberTwo = binding.angka2.text.toString().trim().toIntOrNull()
    }

    private fun validasi(numberOne: Int?, numberTwo: Int?): Boolean {
        if (numberOne == null){
            binding.textInputLayoutAngka1.error = "silahkan di isi colom 1"
            return false
        }else if ( numberTwo == null){
            binding.textInputLayoutAngka2.error = "silahkan di isi colom 2"
            return false
        }
        return true
    }
}