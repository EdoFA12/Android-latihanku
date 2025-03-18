package com.edofa.kotlinkalkulator.RecyclerView

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainTodoListBinding
import java.util.ArrayList

class MainActivityTodoList : AppCompatActivity() {
    private lateinit var binding : ActivityMainTodoListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTodoListBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var itemList = arrayListOf<String>()
        itemList.add("es batu")
        itemList.add("eskopi susu")
        val itemAdapter = ItemAdapter(itemList)

        binding.saveData.setOnClickListener {
            itemList.add(binding.addMenu.text.toString())
            itemAdapter.notifyDataSetChanged()

        }

        val recyclerView:RecyclerView = binding.recylerViewListItem
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter

    }
}