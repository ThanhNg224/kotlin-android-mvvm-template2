package com.thanhng224.app.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhng224.app.core.di.IoDispatcher
import com.thanhng224.app.core.di.MainDispatcher
import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
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
        viewModelScope.launch(ioDispatcher) {
            _loginState.value = _loginState.value.copy(isLoading = true, errorMessage = null)

            when (val result = loginUseCase(_loginState.value.username, _loginState.value.password)) {
                is Result.Success -> {
                    _loginState.value = _loginState.value.copy(isLoading = false)
                    withContext(mainDispatcher) {
                        onSuccess()
                    }
                }
                is Result.Error -> {
                    _loginState.value = _loginState.value.copy(
                        isLoading = false,
                        errorMessage = result.exception.message ?: "Login failed"
                    )
                }
            }
        }
    }
}

