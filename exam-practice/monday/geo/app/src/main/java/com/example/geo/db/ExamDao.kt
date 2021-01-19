package com.example.geo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geo.domain.Exam

@Dao
interface ExamDao {
    @get:Query("select * from exams")
    val exams: LiveData<MutableList<Exam>>

    @get:Query("select count(*) from exams")
    val numberOfExams: Int

    @Insert
    fun addExam(exam: Exam)

    @Insert
    fun addExams(exams: List<Exam>)

    @Delete
    fun deleteExam(e: Exam)

    @Query("delete from exams")
    fun deleteExams()

    @Update
    fun updateExam(e: Exam)


}