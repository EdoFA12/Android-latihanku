package com.edofa.kotlinkalkulator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.edofa.kotlinkalkulator.databinding.ActivityMainAlertBinding
import com.edofa.kotlinkalkulator.databinding.FragmentDialongBuilderBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar.make

class MainActivityAlert : AppCompatActivity() {
    private lateinit var binding : ActivityMainAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAlertBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toast.setOnClickListener {
            Toast.makeText(this, "Ini Toast", Toast.LENGTH_SHORT).show()
        }


        binding.snackbar.setOnClickListener {
            onSnackbar(view)

        }

        binding.alertDialog.setOnClickListener {
            OnAlerDialog(view)

        }

        binding.dialogFragment.setOnClickListener {
            dialogFragment()

        }

    }

    private fun dialogFragment() {
        DialogFragmentBuilder().show(
            supportFragmentManager, DialogFragmentBuilder.TAG)
    }

    private fun OnAlerDialog(view: View) {
        val dialogBuilder =  AlertDialog.Builder(view.context)
        dialogBuilder.setMessage("Apakah Kamu AlertDialog")
        dialogBuilder.setTitle("Alert Dialog")
            .setPositiveButton("Tidak"){ dialog, which ->
                startActivity(Intent(this,MainActivity::class.java))
            }
            .setNegativeButton("Iya"){dialog,which ->
                Toast.makeText(this, "Ternyata kamu Alert Dialog", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()

    }

    private fun onSnackbar(view: View) {
        val snackbar = make(view,"Ini SnackBar", LENGTH_SHORT)
            .setAction("action", null).setTextColor(Color.WHITE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.GRAY)
        val textView =
            snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textSize = 23f
        snackbar.show()

    }



}

