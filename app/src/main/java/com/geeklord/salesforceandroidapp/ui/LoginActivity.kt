package com.geeklord.salesforceandroidapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.geeklord.salesforceandroidapp.R
import com.geeklord.salesforceandroidapp.Utils.SfConfig
import com.geeklord.salesforceandroidapp.Viewmodels.LoginViewModel
import com.geeklord.salesforceandroidapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val authUrl = "${SfConfig.SALESFORCE_AUTH_URL}" +
                    "?response_type=code" +
                    "&client_id=${SfConfig.CLIENT_ID}" +
                    "&redirect_uri=${SfConfig.RAILWAY_CALLBACK_URL}"
            CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(authUrl))
        }

        vm.loggedIn.observe(this) { ok ->
            if (ok) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "$ok", Toast.LENGTH_SHORT).show()
            }
        }
    }
}