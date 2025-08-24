package com.geeklord.salesforceandroidapp.Utils

import android.content.Context

class TokenStorage(context: Context) {
    private val prefs = context.getSharedPreferences("sf_prefs", Context.MODE_PRIVATE)

    var accessToken: String?
        get() = prefs.getString("access_token", null)
        set(value) = prefs.edit().putString("access_token", value).apply()

    var refreshToken: String?
        get() = prefs.getString("refresh_token", null)
        set(value) = prefs.edit().putString("refresh_token", value).apply()

    var instanceUrl: String?
        get() = prefs.getString("instance_url", null)
        set(value) = prefs.edit().putString("instance_url", value).apply()

    fun clear() = prefs.edit().clear().apply()
}