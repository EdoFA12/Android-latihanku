package com.edofa.kotlinkalkulator.RecyclerView
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edofa.kotlinkalkulator.DataModel.DataProduct
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainListProductBinding
import java.util.Locale
import kotlin.collections.ArrayList

class MainActivityListProduct : AppCompatActivity() {
    private lateinit var binding: ActivityMainListProductBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var dataProducts: ArrayList<DataProduct>
    private lateinit var newAdapterProduct: ProductAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListProductBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataProducts = arrayListOf(
            DataProduct(
                "Nasi Lalapan Ayam",
                "15.000",
                "https://cdn.yummy.co.id/content-images/images/20230308/TEE8E5uUubIM1OXeJSjZeoxltsZ7Qn6l-31363738323732303639d41d8cd98f00b204e9800998ecf8427e.jpg?x-oss-process=image/resize,w_388,h_388,m_fixed,x-oss-process=image/format,webp",
                "Lalapan ayam adalah hidangan yang terdiri dari ayam goreng atau bakar yang disajikan bersama lalapan segar dan sambal." +
                        " Lalapan adalah sayuran mentah atau matang, seperti selada, mentimun, daun kemangi, kol, dan tomat, yang biasa disajikan sebagai pelengkap.",
                "Makanan"

            ),
            DataProduct(
                "Nasi Goreng",
                "10.000",
                "https://i0.wp.com/resepkoki.id/wp-content/uploads/2016/09/Resep-Nasi-Goreng-Ikan-Teri.jpg?fit=1920%2C1440&ssl=1",
                "Nasi goreng adalah salah satu makanan khas Indonesia yang terkenal karena rasanya yang gurih," +
                        " sederhana, dan mudah disiapkan. Hidangan ini terbuat dari nasi yang digoreng bersama dengan berbagai bumbu," +
                        " seperti bawang merah, bawang putih, kecap manis, cabai, dan bahan tambahan lain sesuai selera.",
                "Makanan",

                ),
            DataProduct(
                "Nasi Kuning",
                "13.000",
                "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/67/2023/10/19/nasi-kuningok-696626881.jpg",
                "Nasi kuning adalah salah satu hidangan tradisional Indonesia yang terbuat dari beras yang dimasak dengan santan, kunyit, dan rempah-rempah. Warna kuningnya yang cerah berasal dari kunyit, yang juga memberikan aroma harum dan rasa khas." +
                        " Nasi kuning sering disajikan pada acara-acara spesial seperti ulang tahun, syukuran, atau perayaan lainnya.",
                "Makanan"

            ),
            DataProduct(
                "Es Teh",
                "4.000",
                "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/222/2024/08/02/IMG-20240802-WA0149-1433127918.jpg",
                "Esteh adalah minuman yang dibuat dari teh yang disajikan dalam keadaan dingin dengan tambahan es batu. Minuman ini populer di berbagai negara," +
                        " termasuk Indonesia, karena rasanya yang menyegarkan dan mudah dibuat. Biasanya, es teh disajikan dengan tambahan gula untuk memberikan rasa manis" +
                        ", tetapi juga dapat dinikmati tanpa gula bagi mereka yang menginginkan versi yang lebih sehat atau alami.",
                "Minuman"
            ),
            DataProduct(
                "Es Jeruk",
                "6.000",
                "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2022/6/26/eab71001-2e49-4a9a-bc6a-132fc36cf7fb.jpg",
                "Es jeruk peras adalah minuman segar yang dibuat dari perasan jeruk peras, air, gula, dan es batu. ",
                "Minuman"

            ),
            DataProduct(
                "Es Alpukat",
                "11.000",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFzTiP7xIH3wOwW0QqeTEtLQRG-WyFiklIfg&s",
                "Es alpukat adalah minuman atau hidangan penutup yang dibuat dari buah alpukat. Es alpukat bisa disajikan dengan berbagai bahan tambahan, seperti susu, es batu, gula, air kelapa, dan sagu mutiara. Es alpukat cocok disantap saat cuaca panas karena menyegarkan.",
                "Minuman"
            )
        )



        recylerView()
        btnClickItems(view)
        setupSpiner()
        radioFilter()


        binding.addProduct.setOnClickListener {
            binding.linearInput.visibility = View.VISIBLE
            binding.recylerviewProduct.visibility = View.GONE
            val inputNameProduct = binding.edtAddDataProductName.text.toString()
            val inputPriceProduct = binding.edtAddDataProductPrice.text.toString()
            val inputImageProduct = binding.edtAddDataProductImage.text.toString()
            val inputDescProduct = binding.edtAddDataProductDescription.text.toString()
            val inputCategoryProduct = binding.edtAddDataProductCategory.text.toString()
            if (inputNameProduct.isNotEmpty() && inputPriceProduct.isNotEmpty() && inputImageProduct.isNotEmpty() && inputCategoryProduct.isNotEmpty()) {
                binding.recylerviewProduct.visibility = View.VISIBLE
                binding.linearInput.visibility = View.GONE
                newAdapterProduct.notifyDataSetChanged()
                clearData()
                updateRecyclerview(
                    inputNameProduct,
                    inputPriceProduct,
                    inputImageProduct,
                    inputDescProduct,
                    inputCategoryProduct
                )
                btnClickItems(view)

            }

        }

        binding.edtSearchItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString(), "Title")
            }

        })

    }

    private fun setupSpiner() {
        val spinner = binding.categorySpiner
        val categories = dataProducts.map { it.category }.distinct()
        Toast.makeText(this@MainActivityListProduct, "$categories", Toast.LENGTH_SHORT).show()
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, categories
            )
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                filter(parent?.getItemAtPosition(position).toString(),"Category")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }



    private fun filter(text: String , status : String) {
        val filteredNames = ArrayList<DataProduct>()

        if (status.equals("Category")){
            dataProducts.filterTo(filteredNames) {
                it.category.toLowerCase().contains(text.toLowerCase(Locale.ROOT))
            }
        }else if (status.equals("Title")){
            dataProducts.filterTo(filteredNames) {
                it.nama.toLowerCase().contains(text.toLowerCase(Locale.ROOT))
            }
        }else if (status.equals("Price")){
            dataProducts.filterTo(filteredNames) {
                it.harga.toLowerCase().contains(text.toLowerCase(Locale.ROOT))
            }
        }

        newAdapterProduct!!.filterList(filteredNames)
    }


    private fun btnClickItems(view: ConstraintLayout) {
        newAdapterProduct.setOnclickListener(object : ProductAdapter.BtnClickListener {
            override fun onBtnClick(product: DataProduct, status: String?) {
                if (status.equals("Detail")) {
                    sendData(product)
                    Toast.makeText(
                        this@MainActivityListProduct,
                        "Ini ${product.nama} ,$status.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (status.equals("Delete")) {
                    OnAlerDialog(view, product)
                }
            }
        })
    }


    private fun clearData() {
        binding.edtAddDataProductName.text?.clear()
        binding.edtAddDataProductPrice.text?.clear()
        binding.edtAddDataProductImage.text?.clear()
        binding.edtAddDataProductDescription.text?.clear()
        binding.edtAddDataProductCategory.text?.clear()
    }

    private fun recylerView() {
        newRecyclerView = binding.recylerviewProduct
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newAdapterProduct = ProductAdapter(this, dataProducts)
        newRecyclerView.adapter = newAdapterProduct

    }

    private fun sendData(product: DataProduct) {
        val intent = Intent(this@MainActivityListProduct, MainActivityDetailProduct::class.java)
        intent.putExtra("Namakey", product.nama)
        intent.putExtra("Hargakey", product.harga)
        intent.putExtra("Imagekey", product.image)
        intent.putExtra("Descriptionkey", product.description)
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun OnAlerDialog(view: View, product: DataProduct) {
        val dialogBuilder = AlertDialog.Builder(view.context)
        dialogBuilder.setMessage("Apakah Kamu Yakin Ini Menghapus Item")
        dialogBuilder.setTitle("Remove")
            .setNegativeButton("Iya") { dialog, which ->
                dataProducts.remove(product)
                newAdapterProduct.notifyDataSetChanged()
                dialog.dismiss()
                Toast.makeText(
                    this@MainActivityListProduct,
                    "Berhasil Menghapus",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setPositiveButton("Tidak") { dialog, which ->
                Toast.makeText(this, "Tidak Jadi Menghapus", Toast.LENGTH_SHORT).show()

            }

        val dialog = dialogBuilder.create()
        dialog.show()

    }


    private fun updateRecyclerview(
        inputNameProduct: String,
        inputPriceProduct: String,
        inputImageProduct: String,
        inputDescProduct: String,
        inputCategoryProduct: String
    ) {
        recylerView()
        dataProducts.add(
            DataProduct(
                inputNameProduct,
                inputPriceProduct,
                inputImageProduct,
                inputDescProduct,
                inputCategoryProduct
            )
        )

        newAdapterProduct = ProductAdapter(this, dataProducts)

        newRecyclerView.adapter = newAdapterProduct
    }

    fun radioFilter (){
        binding.radioGroup.setOnCheckedChangeListener { group, i ->
            val radio: RadioButton = findViewById(i)
            filter(radio.text.toString().substring(3),"Price")
            Toast.makeText(this, "${radio.text}", Toast.LENGTH_SHORT).show()
        }
    }
}
