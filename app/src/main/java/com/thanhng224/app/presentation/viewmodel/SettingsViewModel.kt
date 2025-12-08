package com.thanhng224.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhng224.app.R
import com.thanhng224.app.core.di.IoDispatcher
import com.thanhng224.app.feature.auth.domain.usecases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false,
    val showTermsDialog: Boolean = false,
    val showPrivacyDialog: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val logoutRequested: Boolean = false,
    val snackbarMessageRes: Int? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onNotificationsToggled(enabled: Boolean) {
        _uiState.update {
            it.copy(
                notificationsEnabled = enabled,
                snackbarMessageRes = if (enabled) {
                    R.string.settings_notifications_enabled_message
                } else {
                    R.string.settings_notifications_disabled_message
                }
            )
        }
    }

    fun onDarkModeToggled(enabled: Boolean) {
        _uiState.update { it.copy(darkModeEnabled = enabled) }
    }

    fun setDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(darkModeEnabled = enabled) }
    }

    fun onShowTermsDialog() {
        _uiState.update { it.copy(showTermsDialog = true) }
    }

    fun onShowPrivacyDialog() {
        _uiState.update { it.copy(showPrivacyDialog = true) }
    }

    fun onDismissTermsDialog() {
        _uiState.update { it.copy(showTermsDialog = false) }
    }

    fun onDismissPrivacyDialog() {
        _uiState.update { it.copy(showPrivacyDialog = false) }
    }

    fun onLogoutDialogShown() {
        _uiState.update { it.copy(showLogoutDialog = true) }
    }

    fun onLogoutDialogDismissed() {
        _uiState.update { it.copy(showLogoutDialog = false) }
    }

    fun onLogoutConfirmed() {
        viewModelScope.launch(ioDispatcher) {
            logoutUseCase()
            _uiState.update {
                it.copy(
                    showLogoutDialog = false,
                    logoutRequested = true
                )
            }
        }
    }

    fun onLogoutHandled() {
        _uiState.update { it.copy(logoutRequested = false) }
    }

    fun onSnackbarShown() {
        _uiState.update { it.copy(snackbarMessageRes = null) }
    }
}
