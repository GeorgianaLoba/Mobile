package com.example.geo.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="exams")
data class Exam(@field: PrimaryKey(autoGenerate=true)
                var id: Int, var name: String?, var group: String?, var details: String?,
                var status: String?, var students: Int?, var type: String?)