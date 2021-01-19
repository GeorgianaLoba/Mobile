package com.example.geo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geo.activityLists.ExamListActivity
import com.example.geo.activityLists.StatsExamListActivity
import com.example.geo.activityLists.StudentExamListActivity
import com.example.geo.databinding.SectionsLayoutBinding

class SectionsActivity: AppCompatActivity() {

    private lateinit var binding: SectionsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SectionsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.teacher.setOnClickListener {
            intent = Intent(this, ExamListActivity::class.java)
            startActivity(intent)
        }
        binding.student.setOnClickListener {

            intent = Intent(this, StudentExamListActivity::class.java)
            startActivity(intent)
        }
        binding.stats.setOnClickListener {

            intent = Intent(this, StatsExamListActivity::class.java)
            startActivity(intent)
        }


    }

}