package com.example.geo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.geo.activityLists.StaffListActivity
import com.example.geo.databinding.SectionsLayoutBinding
import ro.ubbcluj.cs.books.utils.logd

class SectionsActivity: AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private lateinit var binding: SectionsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SectionsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = getSharedPreferences("login", MODE_PRIVATE);

        val str = sp.getString("username", "")
        logd("You are authenticated as: $str")
        if (str.isNullOrBlank()) {
            authenticate()
        }

        binding.staff.setOnClickListener {
            intent = Intent(this, StaffListActivity::class.java)
            startActivity(intent)
        }
//        binding.student.setOnClickListener {
//            intent = Intent(this, EmployeeListActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun authenticate() {
        val taskEditText = EditText(this)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("New User")
        builder.setMessage("Enter your name below")
        builder.setView(taskEditText)
        builder.setPositiveButton(
            "Enter",
            DialogInterface.OnClickListener { dialog, which ->
                val username = taskEditText.text.toString()
                val editor = sp.edit()
                editor.putString("username", username)
                editor.apply()
                dialog.dismiss()
            })
        val alert: AlertDialog = builder.create()
        alert.show()
    }

}