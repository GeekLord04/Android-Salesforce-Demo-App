package com.geeklord.salesforceandroidapp.api

import com.geeklord.salesforceandroidapp.Models.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("services/oauth2/token")
    suspend fun getToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("redirect_uri") redirectUri: String
    ): TokenResponse
}