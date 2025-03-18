package com.edofa.kotlinkalkulator.TransactionIntent

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edofa.kotlinkalkulator.R
import com.edofa.kotlinkalkulator.databinding.ActivityMainTransactionDaftarBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import java.lang.reflect.Modifier

class MainActivityTransactionDaftar : AppCompatActivity() {
    private lateinit var binding : ActivityMainTransactionDaftarBinding
    private var bitmap:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTransactionDaftarBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        val view =  binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataName = intent.getStringExtra("NamaLengkapKey")
        val dataAlamat = intent.getStringExtra("AlamatKey")
        val dataUmur = intent.getStringExtra("UmurKey")

        val bitmap = intent.getParcelableExtra<Bitmap>("bitmapImage")








        binding.fotoPeserta.setImageURI(intent.data)
        binding.fotoPeserta.setImageBitmap(bitmap)
        binding.nama.text = dataName
        binding.alamat.text = dataAlamat
        binding.umur.text = dataUmur



        try {
            if (dataUmur?.toInt()!! <= 18){
                onSnackbar2(view)
                binding.kelayakan.setText("Tidak Layak")
            }else{
                onSnackbar1(view)
                binding.kelayakan.setText("Anda Layak")
            }
        }catch (exception:Exception){
            Toast.makeText(this, "Eror this program", Toast.LENGTH_SHORT).show()
        }









    }


    private fun onSnackbar2(view: View) {
        val snackBar = Snackbar.make(view, "Selamat Anda Tidak layak Bruhh!!!", LENGTH_SHORT)
        snackBar.setTextColor(Color.RED)
        snackBar.setBackgroundTint(Color.TRANSPARENT)
        val textSet = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textSet.textSize = 30f
        snackBar.show()

    }


    private fun onSnackbar1(view: View) {
        val snackBar = Snackbar.make(view, "Selamat Anda Layak", LENGTH_SHORT)
        snackBar.setTextColor(Color.rgb(106, 90, 205))
        snackBar.setBackgroundTint(Color.TRANSPARENT)
        val textSet = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textSet.textSize = 30f
        snackBar.show()

    }

}