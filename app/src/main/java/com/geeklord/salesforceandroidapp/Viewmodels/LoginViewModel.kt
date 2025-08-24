package com.geeklord.salesforceandroidapp.Viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeklord.salesforceandroidapp.Repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean> = _loggedIn

    fun exchangeCode(code: String) {
        viewModelScope.launch {
            try {
                repo.exchangeCode(code)
                _loggedIn.postValue(true)
            } catch (e: Exception) {
                _loggedIn.postValue(false)
            }
        }
    }
}