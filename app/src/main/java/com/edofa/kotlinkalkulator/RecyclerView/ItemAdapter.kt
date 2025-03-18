package com.edofa.kotlinkalkulator.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edofa.kotlinkalkulator.R


class ItemAdapter(
    private val itemList :ArrayList<String>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val tvName:TextView = view.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = itemList[position]


    }
    override fun getItemCount() = itemList.size
}