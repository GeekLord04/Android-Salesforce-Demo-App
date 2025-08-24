package com.geeklord.salesforceandroidapp.Models

data class AccountResponse(
    val totalSize: Int,
    val done: Boolean,
    val records: List<Account>
)
