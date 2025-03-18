package com.edofa.kotlinkalkulator

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.edofa.kotlinkalkulator.TransactionIntent.MainActivityTransactionDaftar
import com.edofa.kotlinkalkulator.databinding.ActivityMainPendaftaranMahasiswaBinding
import java.util.Calendar

class MainActivityPendaftaranMahasiswa : AppCompatActivity() {
    private val binding: ActivityMainPendaftaranMahasiswaBinding by lazy { ActivityMainPendaftaranMahasiswaBinding.inflate(layoutInflater) }
    private var umur : Int? = null
    private val SELLECT_IMAGE = 101
    private val CAMERA_REQUEST_CODE = 102
    private var uri:Uri? = null
    private var bitmap: Bitmap? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            var tahun = year
            binding.tanggalLahir.setText(" " +  dayOfMonth + " " + monthOfYear + " " + tahun )
            try {
                umur = 2024 - year
            }catch (exception:Exception){
                Toast.makeText(this, "Isi ulang", Toast.LENGTH_SHORT).show()
            }

        }, year, month, day)


        binding.tanggalLahir.setOnClickListener {
            dpd.show()

        }

        binding.daftar.setOnClickListener {
            val namaLengkap: String = binding.namaLengkap.text.toString()
            val alamat: String = binding.alamat.text.toString()

            val intent = Intent(this,MainActivityTransactionDaftar::class.java)
            intent.putExtra("NamaLengkapKey", namaLengkap)
            intent.putExtra("AlamatKey", alamat)
            intent.setData(uri)
            intent.putExtra("bitmapImage",  bitmap)
            intent.putExtra("UmurKey",umur.toString())
            startActivity(intent)
        }


        binding.selectImage.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELLECT_IMAGE)
        }


        binding.takePhoto.setOnClickListener {
            val callCameraIntent =Intent()
            callCameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
            if (callCameraIntent != null) {
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELLECT_IMAGE && null != data ){
            uri = data.data
            binding.imgGallery.setImageURI(uri)

        }

//        when(requestCode){
//            CAMERA_REQUEST_CODE -> {
//                if (resultCode == Activity.RESULT_OK && data != null){
//                    bitmap = data.extras?.get("data") as Bitmap
//
//                    binding.imgGallery.setImageBitmap(bitmap)
//                }
//            }
//            else -> {
//                Toast.makeText(this, "Kode tidak tersedia/tidak di kenal", Toast.LENGTH_SHORT).show()
//            }
//        }



    }


}