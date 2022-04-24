package com.instant.hospital.Models

data class ModelAllUsers(
    val `data`: List<UserData>,
    val message: String,
    val status: Int
)

data class UserData(
    val avatar: String,
    val first_name: String,
    val id: Int,
    val type: String
)