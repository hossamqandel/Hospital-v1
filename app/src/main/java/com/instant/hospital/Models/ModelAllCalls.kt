package com.instant.hospital.Models

data class ModelAllCalls(
    val `data`: List<CallsData>,
    val message: String,
    val status: Int
)

data class CallsData(
    val created_at: String,
    val id: Int,
    val patient_name: String,
    val status: String
)