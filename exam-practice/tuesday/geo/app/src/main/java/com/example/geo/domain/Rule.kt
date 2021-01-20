package com.example.geo.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="rules")
data class Rule(@field: PrimaryKey(autoGenerate=true)
                var id: Int, var name: String?, var level: Int?, var status: String?,
                var from: Int?, var to: Int?)