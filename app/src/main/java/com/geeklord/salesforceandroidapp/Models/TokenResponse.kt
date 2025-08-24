package com.geeklord.salesforceandroidapp.Models

data class TokenResponse(
    val access_token: String,
    val refresh_token: String?,
    val instance_url: String,
    val id: String?,
    val token_type: String?,
    val issued_at: String?,
    val signature: String?
)
