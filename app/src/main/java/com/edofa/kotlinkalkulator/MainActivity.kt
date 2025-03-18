package com.edofa.kotlinkalkulator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.ApiByRetrofit.Activity.MainActivityGetApiByRetrofit
import com.edofa.kotlinkalkulator.RecyclerView.MainActivityListMenu
import com.edofa.kotlinkalkulator.RecyclerView.MainActivityListProduct
import com.edofa.kotlinkalkulator.RecyclerView.MainActivityTodoList
import com.edofa.kotlinkalkulator.TransactionIntent.MainActivityTransactionIntent
import com.edofa.kotlinkalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:  ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.counterApp.setOnClickListener {
            val counterApp = Intent(this,MainActivityCounterApp::class.java)
            startActivity(counterApp)
        }

        binding.kalkulator.setOnClickListener {
            val kalkulator = Intent(this,MainActivityKalkulator::class.java)
            startActivity(kalkulator)

        }

        binding.studentGrade.setOnClickListener {
            startActivity(Intent(this, MainActivityStudentGrade::class.java))
        }


        binding.alert.setOnClickListener {
            startActivity(Intent( this, MainActivityAlert::class.java))
        }

        binding.studentGradeAgain.setOnClickListener {
            startActivity(Intent(this, MainActivityStundentGradeAgain::class.java))
        }


        binding.transactionIntent.setOnClickListener {
            startActivity(Intent(this, MainActivityTransactionIntent::class.java))
        }


        binding.pendaftaran.setOnClickListener {
            startActivity(Intent(this,MainActivityPendaftaranMahasiswa::class.java))
        }

        binding.sharePreferences.setOnClickListener {
            startActivity(Intent(this,MainActivitySharePreferences::class.java))
        }

        binding.recylerview.setOnClickListener {
            startActivity(Intent(this, MainActivityTodoList::class.java))
        }

        binding.listProduct.setOnClickListener {
            startActivity(Intent(this,MainActivityListProduct::class.java))
        }

        binding.listMenuSearch.setOnClickListener {
            startActivity(Intent(this, MainActivityListMenu::class.java))
        }


        binding.apiRetrofit.setOnClickListener {
            startActivity(Intent(this, MainActivityGetApiByRetrofit::class.java))
        }


    }
}