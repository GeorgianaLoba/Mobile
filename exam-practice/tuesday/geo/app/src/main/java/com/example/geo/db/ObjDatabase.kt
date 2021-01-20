package com.example.geo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.geo.domain.Rule

@Database(entities = [Rule::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ObjDatabase: RoomDatabase() {
    abstract val objDao: ObjDao
}