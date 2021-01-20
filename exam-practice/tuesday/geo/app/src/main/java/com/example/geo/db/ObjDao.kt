package com.example.geo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geo.domain.Rule

@Dao
interface ObjDao {
    @get:Query("select * from rules")
    val objects: LiveData<MutableList<Rule>>

    @get:Query("select count(*) from rules")
    val numberOfObjects: Int

    @Insert
    fun addObj(exam: Rule)

    @Insert
    fun addObjects(exams: List<Rule>)

    @Delete
    fun deleteObj(e: Rule)

    @Query("delete from rules")
    fun deleteObjects()

    @Update
    fun updateObj(e: Rule)
}