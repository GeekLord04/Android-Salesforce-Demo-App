package com.geeklord.salesforceandroidapp.Repository

import com.geeklord.salesforceandroidapp.Utils.SfConfig
import com.geeklord.salesforceandroidapp.Utils.TokenStorage
import com.geeklord.salesforceandroidapp.api.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val storage: TokenStorage
) {
    suspend fun exchangeCode(code: String): Boolean {
        val tokenResp = authApi.getToken(
            grantType = "authorization_code",
            code = code,
            clientId = SfConfig.CLIENT_ID,
            clientSecret = SfConfig.CLIENT_SECRET,
            redirectUri = SfConfig.RAILWAY_CALLBACK_URL
        )
        storage.accessToken = tokenResp.access_token
        storage.refreshToken = tokenResp.refresh_token
        storage.instanceUrl = tokenResp.instance_url
        return true
    }
}
