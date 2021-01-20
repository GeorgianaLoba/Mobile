package com.example.geo.service

import com.example.geo.domain.Rule
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {
    @GET("/rules")
    suspend fun getRules(): List<Rule>

    @POST("/rule")
    suspend fun addRule(@Body e: Rule): Rule

    @GET("/rule/{id}")
    suspend fun getRule(@Path("id") id: Int): Rule

//    @GET("/draft")
//    suspend fun getDraftExams(): List<Exam>
//
//    @POST("/join")
//    suspend fun joinExam(@Body e: Exam)
//
//    @GET("/group/{name}")
//    suspend fun getStatsExams(@Path("name") name: String): List<Exam>

    companion object {
        const val SERVICE_ENDPOINT = "http://10.0.2.2:2019"
    }
}