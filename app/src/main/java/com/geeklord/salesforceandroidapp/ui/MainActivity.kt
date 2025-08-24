package com.geeklord.salesforceandroidapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeklord.salesforceandroidapp.R
import com.geeklord.salesforceandroidapp.Viewmodels.AccountViewModel
import com.geeklord.salesforceandroidapp.databinding.ActivityMainBinding
import com.geeklord.salesforceandroidapp.ui.adapter.AccountAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: AccountViewModel by viewModels()
    private val adapter = AccountAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAccounts.layoutManager = LinearLayoutManager(this)
        binding.rvAccounts.adapter = adapter

        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        binding.btnLoadAccounts.setOnClickListener { vm.loadAccounts() }

        vm.accounts.observe(this) { list ->
            adapter.submitList(list)
        }

        // initial load if token present
        vm.loadAccounts()
    }
}