package com.thanhng224.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhng224.app.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.setLoggedIn(false)
            onLogoutSuccess()
        }
    }
}

