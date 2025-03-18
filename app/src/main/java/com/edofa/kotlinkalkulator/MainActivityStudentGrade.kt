package com.edofa.kotlinkalkulator

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.databinding.ActivityMainStudentGradeBinding

class MainActivityStudentGrade : AppCompatActivity() {
    private val binding: ActivityMainStudentGradeBinding by lazy { ActivityMainStudentGradeBinding.inflate(layoutInflater) }

    private var nilaiTugas: Int? = null
    private var nilaiUts: Int? = null
    private var nilaiUas: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.tentukanNilai.setOnClickListener {
            resetInput()
            cekInputValue()
            if (validasiNilai(nilaiTugas, nilaiUts, nilaiUas)) {
                valueDetermination(nilaiTugas, nilaiUts, nilaiUas)
            }
        }
    }

    private fun resetInput() {
        binding.textInputLayoutNilaiTugas.error = null
        binding.textInputLayoutNilaiUts.error = null
        binding.textInputLayoutNilaiUas.error = null
    }

    private fun valueDetermination(nilaiTugas: Int?, nilaiUts: Int?, nilaiUas: Int?) {
        val resulsTugas: Double = nilaiTugas?.toDouble()!! * 30 / 100
        val resultUts: Double = nilaiUts?.toDouble()!! * 30 / 100
        val resultUas: Double = nilaiUas?.toDouble()!! * 40 / 100

        var result: Double = resulsTugas!! + resultUts!! + resultUas!!

        if (result >= 60) {
            binding.penentuanLulus.setText("selamat anda lulus")
            binding.penentuanNilai.setText(result.toString())
        } else binding.penentuanLulus.setText("selamat anda Tidak lulus")
        binding.penentuanNilai.setText(result.toString())
    }

    private fun cekInputValue() {
        nilaiTugas = binding.nilaiTugas.text.toString().trim().toIntOrNull()
        nilaiUts = binding.nilaiUts.text.toString().trim().toIntOrNull()
        nilaiUas = binding.nilaiUas.text.toString().trim().toIntOrNull()
    }

    private fun validasiNilai(nilaiTugas: Int?, nilaiUts: Int?, nilaiUas: Int?): Boolean {

        if (nilaiTugas == null) {
            binding.textInputLayoutNilaiTugas.error = "Silahkan Di isi Nilai Tugasnya"
            return false
        } else if (nilaiUts == null) {
            binding.textInputLayoutNilaiUts.error = "Silahkan Di isi Nilai Utsnya"
            return false
        } else if (nilaiUas == null) {
            binding.textInputLayoutNilaiUas.error = "Silahkan Di isi Nilai Uasnya"
            return false
        }
        return true
    }


}