package com.edofa.kotlinkalkulator.ApiByRetrofit.ApiAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel.ResponseApi
import com.edofa.kotlinkalkulator.R
import com.google.android.material.imageview.ShapeableImageView

class AdapterProductApi(
    private val itemProductApi :ArrayList<ResponseApi>):RecyclerView.Adapter<AdapterProductApi.ViewHolder>() {
        lateinit var btnClickListener: BtnClickListenerApiProduct
        fun setOnClickListener(btnClickListenerApiProduct: BtnClickListenerApiProduct){
            this.btnClickListener = btnClickListenerApiProduct
        }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

        var productName = view.findViewById<TextView>(R.id.nama_product)
        var productPrice = view.findViewById<TextView>(R.id.harga_product)
        var titleImage = view.findViewById<ShapeableImageView>(R.id.title_image_product)
        var categoryitem = view.findViewById<TextView>(R.id.category_product)
        var delateItem = view.findViewById<ImageButton>(R.id.delete_item_product_api)
    }

    interface BtnClickListenerApiProduct {
        fun onBtnClickListener(product: ResponseApi, status: String?, position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_product_api, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemProductApi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemProductApi[position]
        val rawImageUrl = currentItem.images?.firstOrNull()
        val imageUrl = rawImageUrl?.replace("[\"", "")?.replace("\"]", "")?.trim()
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.titleImage)
        holder.productName.text = currentItem.title
        holder.categoryitem.text = currentItem.category?.name
        holder.productPrice.setText("$ ${currentItem.price.toString()}")
        holder.delateItem.setOnClickListener {
            btnClickListener.onBtnClickListener(currentItem, "delate", position)
        }


    }
}


