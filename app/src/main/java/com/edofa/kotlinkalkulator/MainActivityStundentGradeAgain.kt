package com.edofa.kotlinkalkulator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.databinding.ActivityMainStundentGradeAgainBinding

class MainActivityStundentGradeAgain : AppCompatActivity() {
    private val binding : ActivityMainStundentGradeAgainBinding by lazy { ActivityMainStundentGradeAgainBinding.inflate(layoutInflater) }
    private var nilaiTugas : Int? = null
    private var nilaiUts : Int? = null
    private var nilaiUas : Int? = null


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
            cekNumber()
            closeEror()
            if (validationinput(nilaiTugas,nilaiUts,nilaiUas)){
                returnGrade(nilaiTugas,nilaiUts,nilaiUas)
            }

        }

    }

    private fun closeEror() {
        binding.textInputLayoutNilaiTugas.error = ""
        binding.textInputLayoutNilaiUts.error = ""
        binding.textInputLayoutNilaiUas.error = ""


    }

    private fun returnGrade(nilaiTugas: Int?, nilaiUts: Int?, nilaiUas: Int?) {
        val conversionNilaiTugas = nilaiTugas?.toDouble()!! * 30/100
        val conversionNilaiUts = nilaiUts?.toDouble()!! * 30/100
        val conversionNilaiUas = nilaiUas?.toDouble()!! * 40/100

        val result = conversionNilaiTugas + conversionNilaiUts + conversionNilaiUas


        if (result >= 60){
            binding.nilai.setText(result.toString())
            binding.keputusan.setText("lulus")
        }else{
            binding.nilai.setText(result.toString())
            binding.keputusan.setText("Tidak lulus")
        }

    }

    private fun cekNumber() {
        nilaiTugas = binding.nilaiTugas.text.toString().toIntOrNull()
        nilaiUts = binding.nilaiUts.text.toString().toIntOrNull()
        nilaiUas = binding.nilaiUas.text.toString().toIntOrNull()
    }



    private fun validationinput(nilaiTugas: Int?, nilaiUts: Int?, nilaiUas: Int?): Boolean {

        if (nilaiTugas == null){
            binding.textInputLayoutNilaiTugas.error = "Silahkan DiIsi Nilai Tugas"
            return false
        }else if ( nilaiUts == null){
            binding.textInputLayoutNilaiUts.error = "Silahkan DiIsi Nilai Uts"
            return false
        }else if ( nilaiUas == null){
            binding.textInputLayoutNilaiUas.error = "Silahkan DiIsi Nilai Uas"
            return false
        }
        return true
    }
}