package com.edofa.kotlinkalkulator.RecyclerView

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainListMenuBinding

class MainActivityListMenu : AppCompatActivity() {
    private lateinit var binding : ActivityMainListMenuBinding
    private lateinit var itemsListMenu: ArrayList<String>
    private lateinit var recyclerViewMenu : RecyclerView
    private lateinit var  adapterMenu : MenuAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainListMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        itemsListMenu = arrayListOf()

        itemsListMenu.add("Es Teh")
        itemsListMenu.add("Es Kopi Susu")
        itemsListMenu.add("Nasi Goreng")
        itemsListMenu.add("Jus Semangka")
        itemsListMenu.add("Ayam Geprek")


        recyclerViewMenu = binding.recylerviewItem
        recyclerViewMenu.layoutManager = LinearLayoutManager(this)
        recyclerViewMenu.setHasFixedSize(true)
        adapterMenu  = MenuAdapter(itemsListMenu)
        recyclerViewMenu.adapter = adapterMenu





        binding.edtSearchItem.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })
    }

    private fun filter(text: String) {
        val filteredNames = ArrayList<String>()
        itemsListMenu.filterTo(filteredNames) {
            it.lowercase().contains(text)
        }
        adapterMenu!!.filterList(filteredNames)
    }
}