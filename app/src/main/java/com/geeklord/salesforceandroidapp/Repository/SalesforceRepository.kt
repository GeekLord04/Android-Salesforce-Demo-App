package com.geeklord.salesforceandroidapp.Repository

import com.geeklord.salesforceandroidapp.Utils.TokenStorage
import com.geeklord.salesforceandroidapp.api.SalesforceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalesforceRepository @Inject constructor(
    private val storage: TokenStorage
) {

    private fun buildApi(): SalesforceApi {
        val token = storage.accessToken ?: ""

        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                val req = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(req)
            }).build()

        val base = storage.instanceUrl ?: "https://login.salesforce.com/"
        return Retrofit.Builder()
            .baseUrl(if (base.endsWith("/")) base else "$base/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SalesforceApi::class.java)
    }

    suspend fun getAccounts() = buildApi().queryAccounts().records

    suspend fun createAccount(name: String, industry: String?, phone: String?) =
        buildApi().createAccount(mapOf("Name" to name, "Industry" to (industry ?: ""), "Phone" to (phone ?: ""))).isSuccessful
}