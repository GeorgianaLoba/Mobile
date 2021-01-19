package com.example.geo.service

import com.example.geo.domain.Exam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExamService {
    @GET("/exams")
    suspend fun getExams(): List<Exam>

    @POST("/exam")
    suspend fun addExam(@Body e: Exam): Exam

    @GET("/exam/{id}")
    suspend fun getExam(@Path("id") id: Int): Exam

    @GET("/draft")
    suspend fun getDraftExams(): List<Exam>

    @POST("/join")
    suspend fun joinExam(@Body e: Exam)

    @GET("/group/{name}")
    suspend fun getStatsExams(@Path("name") name: String): List<Exam>

    companion object {
        const val SERVICE_ENDPOINT = "http://10.0.2.2:2018"
    }
}