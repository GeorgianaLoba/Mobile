package com.example.geo

import android.app.Application
import androidx.room.Room
import com.example.geo.db.ExamDatabase

//todo: db initialised only once, in the application!
class ExamApp: Application() {
    lateinit var db: ExamDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            ExamDatabase::class.java, "database-name").build()
    }
}