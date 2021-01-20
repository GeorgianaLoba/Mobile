package com.example.geo

import android.app.Application
import androidx.room.Room
import com.example.geo.db.ObjDatabase

//todo: db initialised only once, in the application!
class MainApp: Application() {
    lateinit var db: ObjDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            ObjDatabase::class.java, "database-name").build()
    }
}