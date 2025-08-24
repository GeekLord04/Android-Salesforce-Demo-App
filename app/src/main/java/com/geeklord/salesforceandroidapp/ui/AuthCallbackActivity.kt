package com.geeklord.salesforceandroidapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.geeklord.salesforceandroidapp.Viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthCallbackActivity : AppCompatActivity() {

    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: Uri? = intent?.data
        val code = data?.getQueryParameter("code")
        if (code != null) {
            vm.exchangeCode(code)
            vm.loggedIn.observe(this) { ok ->
                if (ok) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
//                    finish()
                    Toast.makeText(this, "$ok", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
//            finish()
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}