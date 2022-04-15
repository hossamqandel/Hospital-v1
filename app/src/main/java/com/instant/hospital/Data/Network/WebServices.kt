package com.instant.hospital.Data.Network

import com.instant.hospital.Models.ModelUser
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * @property This interface is the only one responsible for handling
 * all API requests for this application
 * */

interface WebServices {

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


    @POST("login")
    suspend fun login(
        @Field("email")email: String,
        @Field("password")password: String,
        @Field("device_token")device_token: String
    ): ModelUser





}