package com.thanhng224.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhng224.app.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onUsernameChange(username: String) {
        _loginState.value = _loginState.value.copy(username = username, errorMessage = null)
    }

    fun onPasswordChange(password: String) {
        _loginState.value = _loginState.value.copy(password = password, errorMessage = null)
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loginState.value = _loginState.value.copy(isLoading = true, errorMessage = null)

            // Dummy validation - ready to be replaced with API call
            if (_loginState.value.username == "admin" && _loginState.value.password == "1234") {
                repository.setLoggedIn(true)
                _loginState.value = _loginState.value.copy(isLoading = false)
                onSuccess()
            } else {
                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                    errorMessage = "Invalid username or password"
                )
            }
        }
    }
}

