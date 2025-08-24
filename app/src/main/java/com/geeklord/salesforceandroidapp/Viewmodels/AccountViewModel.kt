package com.geeklord.salesforceandroidapp.Viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeklord.salesforceandroidapp.Models.Account
import com.geeklord.salesforceandroidapp.Repository.SalesforceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repo: SalesforceRepository
) : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = _accounts

    fun loadAccounts() {
        viewModelScope.launch {
            try {
                _accounts.value = repo.getAccounts()
            } catch (e: Exception) {
                _accounts.value = emptyList()
            }
        }
    }

    fun createAccount(name: String, industry: String?, phone: String?) {
        viewModelScope.launch {
            val ok = repo.createAccount(name, industry, phone)
            if (ok) loadAccounts()
        }
    }
}