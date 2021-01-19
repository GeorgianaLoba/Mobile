package com.example.geo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.geo.domain.Exam

@Database(entities = [Exam::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExamDatabase: RoomDatabase() {
    abstract val examDao: ExamDao
}