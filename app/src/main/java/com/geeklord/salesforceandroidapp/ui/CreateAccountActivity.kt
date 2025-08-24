package com.geeklord.salesforceandroidapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.geeklord.salesforceandroidapp.R
import com.geeklord.salesforceandroidapp.Viewmodels.AccountViewModel
import com.geeklord.salesforceandroidapp.databinding.ActivityCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    private val vm: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveAccount.setOnClickListener {
            val name = binding.etAccountName.text.toString().trim()
            val industry = binding.etIndustry.text.toString().trim().ifBlank { null }
            val phone = binding.etPhone.text.toString().trim().ifBlank { null }

            if (name.isEmpty()) {
                Toast.makeText(this, "Name required", Toast.LENGTH_SHORT).show()
            } else {
                vm.createAccount(name, industry, phone)
                finish()
            }
        }

    }
}