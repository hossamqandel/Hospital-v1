package com.instant.hospital.Data.Network

import com.instant.hospital.Models.*
import retrofit2.http.*

/**
 * @property This interface is the only one responsible for handling
 * all API requests for this application
 * */

interface WebServices {

    @FormUrlEncoded
    @POST("register")
    suspend fun createUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("gender") gender: String,
        @Field("specialist") specialist: String,
        @Field("birthday") birthday: String,
        @Field("status") status: String,
        @Field("address") address: String,
        @Field("mobile") mobile: String,
        @Field("type") type: String
    ): ModelUser


    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String = "fakeDeviceToken"
    ): ModelUser


    //    @FormUrlEncoded
    @GET("doctors")
    suspend fun getAllUsers(@Query("type") type: String): ModelAllUsers


    @GET("calls")
    suspend fun getAllCallsByDate(@Query("date") date: String): ModelAllCalls


    @FormUrlEncoded
    @POST("calls")
    suspend fun createCall(
        @Field("patient_name") patient_name: String,
        @Field("doctor_id") doctor_id: Int,
        @Field("age") age: String,
        @Field("phone") phone: String,
        @Field("description") description: String
    ): ModelCreateCall


    @GET("case/{id}")
    suspend fun getSingleCaseDetailsByCaseId(@Path("id") caseId: Int): ModelCaseDetails


    @FormUrlEncoded
    @POST("add-nurse")
    suspend fun setNurseRequestFromDoctor(
        @Field("call_id") callId: Int,
        @Field("user_id") userId: Int
    ): ModelResponse


    @FormUrlEncoded
    @PUT("calls-accept")
    suspend fun acceptRejectCall(@Field("status") status: String): ModelResponse


    @GET("tasks")
    suspend fun getAllTasks(@Query("date") date: String): ModelAllTasks

    @FormUrlEncoded
    @POST("measurement")
    suspend fun addMeasurement(
        @Field("call_id") callId: Int,
        @Field("blood_pressure") bloodPressure: String,
        @Field("sugar_analysis") sugarAnalysis: String,
        @Field("note") note: String,
        @Field("status") status: String
    ): ModelCaseDetails
}