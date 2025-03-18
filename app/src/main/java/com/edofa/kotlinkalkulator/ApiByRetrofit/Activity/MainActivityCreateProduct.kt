package com.edofa.kotlinkalkulator.ApiByRetrofit.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isNotEmpty
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel.RequestApi
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel.ResponseApi
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiService.ApiServiceProduct
import com.edofa.kotlinkalkulator.ApiByRetrofit.RetrofitCreate.RetrofitCreate
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainCreateProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityCreateProduct : AppCompatActivity() {
    private val binding : ActivityMainCreateProductBinding by lazy {
        ActivityMainCreateProductBinding.inflate(layoutInflater)
    }
    private lateinit var productApi : ApiServiceProduct


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.create.setOnClickListener {
            var name = binding.textInputEdtTitle.text.toString().trim()
            var price = binding.textInputEdtPrice.text.toString().trim()
            var desc = binding.textInputEdtDescription.text.toString().trim()
            var category = binding.textInputEdtCategory.text.toString().trim()
            var image = binding.textInputEdtImage.text.toString().trim()

            if (name.isEmpty() || price.isEmpty() || desc.isEmpty() || category.isEmpty() || image.isEmpty()){
                Toast.makeText(this, "Silahkan Lengkapi Data Di Atas", Toast.LENGTH_SHORT).show()


            }else{
                createProduct(name,price,desc,category,image)

            }

        }


    }


    


    private fun createProduct(
        name: String,
        price: String,
        desc: String,
        category: String,
        image: String
    ) {
        productApi = RetrofitCreate.getInstance().create(ApiServiceProduct::class.java)

        val dataRequestApi = RequestApi(
            images = listOf(image),
            title = name,
            price = price.toInt(),
            categoryId = category.toInt(),
            description = desc
        )

        val call = productApi.createProduct(dataRequestApi)
        call.enqueue(object : Callback<ResponseApi>{
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                Log.d("TAG", "onResponsetes:${response.body()} ")
                startActivity(Intent(this@MainActivityCreateProduct, MainActivityGetApiByRetrofit::class.java))
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.d("TAG", "onFailure:${t.printStackTrace()} ")
            }

        })

    }

}

