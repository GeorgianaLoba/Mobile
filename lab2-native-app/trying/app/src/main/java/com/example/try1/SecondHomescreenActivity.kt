package com.example.try1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SecondHomescreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_homescreen_activity)
        println("HOT IN HERE")
    }

    fun go_list_view(view: View) {
        intent = Intent(this, ListViewActivity::class.java)
        startActivity(intent)
    }

}