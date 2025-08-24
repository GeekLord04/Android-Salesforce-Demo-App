package com.geeklord.salesforceandroidapp.api

import com.geeklord.salesforceandroidapp.Models.AccountResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SalesforceApi {

    @GET("/services/data/v62.0/query")
    suspend fun queryAccounts(@Query("q") q: String = "SELECT Id,Name,Industry,Phone FROM Account"): AccountResponse

    @POST("/services/data/v62.0/sobjects/Account")
    suspend fun createAccount(@Body body: Map<String, String>): Response<ResponseBody>
}