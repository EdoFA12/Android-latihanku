package com.edofa.kotlinkalkulator.ApiByRetrofit.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiAdapter.AdapterProductApi
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiModel.ResponseApi
import com.edofa.kotlinkalkulator.ApiByRetrofit.ApiService.ApiServiceProduct
import com.edofa.kotlinkalkulator.ApiByRetrofit.RetrofitCreate.RetrofitCreate
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainGetApiByRetrofitBinding
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityGetApiByRetrofit : AppCompatActivity() {
    private val binding : ActivityMainGetApiByRetrofitBinding by lazy {
        ActivityMainGetApiByRetrofitBinding.inflate(layoutInflater)
    }
    private lateinit var productApi : ApiServiceProduct
    private lateinit var recyclerViewMenu : RecyclerView
    private lateinit var adapterProductApi: AdapterProductApi
    private lateinit var dataProduct : ArrayList<ResponseApi>


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
        getProduct()




        binding.tambahProduct.setOnClickListener {
            startActivity(Intent(this, MainActivityCreateProduct::class.java))
        }

    }

    private fun delateProduct() {
        adapterProductApi.setOnClickListener(object : AdapterProductApi.BtnClickListenerApiProduct {
            override fun onBtnClickListener(product: ResponseApi, status: String?, position: Int) {
                if (status.equals("delate")){
                    productApi = RetrofitCreate.getInstance().create(ApiServiceProduct::class.java)
                    dialogDelate(product, position)
                }
            }

        })



    }

    private fun dialogDelate(product:ResponseApi, position:Int) {
        AwesomeDialog.build(this@MainActivityGetApiByRetrofit)
            .title(product.title!!)
            .body("Apakah Ada Ingin Menghapus Item Ini")
            .onPositive("Hapus",buttonBackgroundColor = R.drawable.bg_roundtext) {
                val call : Call<ResponseBody> = productApi.delateProduct(product.id!!)
                call.enqueue(object : Callback<ResponseBody>{
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        response.body()
                        Log.d("TAG", "onResponseDelate: ${response.body()}")
                        dataProduct.removeAt(position)
                        adapterProductApi.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.d("TAG", "onFailure: ${t.printStackTrace()}")
                    }

                })
            }
            .onNegative("Tidak",buttonBackgroundColor = R.drawable.bg_roundtext) {
                Toast.makeText(this@MainActivityGetApiByRetrofit, "Tidak Jadi menghapus", Toast.LENGTH_SHORT).show()
            }
    }


    fun getProduct(){
        productApi =  RetrofitCreate.getInstance().create(ApiServiceProduct::class.java)
        val call: Call<ArrayList<ResponseApi>> = productApi.getProduct()
        call.enqueue(object : Callback<ArrayList<ResponseApi>> {
            override fun onResponse(
                call: Call<ArrayList<ResponseApi>>,
                response: Response<ArrayList<ResponseApi>>
            ) {
                Log.d("TAG", "onResponse: ${response.body()}")
                binding.animationView
                for (i in response.body()!!){
                    Log.d("TAG", "onResponse: ${i.images}")
                    recyclerViewMenu = binding.recylerviewProduct
                    recyclerViewMenu.layoutManager = LinearLayoutManager(this@MainActivityGetApiByRetrofit)
                    recyclerViewMenu.setHasFixedSize(true)
                    adapterProductApi  = AdapterProductApi(response.body()!!)
                    recyclerViewMenu.adapter = adapterProductApi
                    binding.tambahProduct.visibility = View.VISIBLE
                    binding.animationView.visibility = View.GONE
                    delateProduct()
                }
            }
            override fun onFailure(call: Call<ArrayList<ResponseApi>>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.printStackTrace()}")
            }
        })
    }
}
