package com.example.try1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
        setContentView(R.layout.activity_main)

    }

    fun go_homescreen(view: View) {
        intent = Intent(this, SecondHomescreenActivity::class.java)
        startActivity(intent)
    }


}