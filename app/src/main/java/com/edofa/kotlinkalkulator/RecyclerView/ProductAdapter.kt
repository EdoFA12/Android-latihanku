package com.edofa.kotlinkalkulator.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edofa.kotlinkalkulator.DataModel.DataProduct
import com.edofa.kotlinkalkulator.R
import com.google.android.material.imageview.ShapeableImageView


open class ProductAdapter
    (val context: Context,var listItemProduct: ArrayList<DataProduct> ):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    lateinit var btnlistener: BtnClickListener
    fun setOnclickListener(btnClickListener: BtnClickListener){
        this.btnlistener = btnClickListener

    }


    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        var productName = view.findViewById<TextView>(R.id.nama_product)
        var productPrice = view.findViewById<TextView>(R.id.harga_product)
        var titleImage = view.findViewById<ShapeableImageView>(R.id.title_image_product)
        var layoutItem = view.findViewById<CardView>(R.id.layout_item)
        var categoryitem = view.findViewById<TextView>(R.id.category_product)
        var deleteItem = view.findViewById<ImageView>(R.id.delete_item_product)
    }

    interface BtnClickListener {
        fun onBtnClick(product: DataProduct, status: String?)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItemProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listItemProduct[position]
        Glide.with(holder.itemView).load(currentItem.image).into(holder.titleImage)
        holder.productName.text = currentItem.nama
        holder.categoryitem.text = currentItem.category
        holder.productPrice.text = currentItem.harga
        holder.layoutItem.setOnClickListener {
            btnlistener.onBtnClick(currentItem,"Detail")
        }
        holder.deleteItem.setOnClickListener {
            btnlistener.onBtnClick(currentItem,"Delete")
        }

    }

    fun filterList(filteredNames: ArrayList<DataProduct>){
        this.listItemProduct = filteredNames
        notifyDataSetChanged()
    }




}