package com.edofa.kotlinkalkulator.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edofa.kotlinkalkulator.R

class MenuAdapter (
    var itemsMenu: ArrayList<String>
): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nama = itemView.findViewById<TextView>(R.id.nama_menu)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_menu,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama.text = itemsMenu[position]
    }

    override fun getItemCount(): Int {
        return itemsMenu.size
    }


    fun filterList(filteredNames: ArrayList<String>) {
        this.itemsMenu = filteredNames
        notifyDataSetChanged()
    }
}