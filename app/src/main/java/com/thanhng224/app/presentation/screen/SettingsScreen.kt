package com.thanhng224.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thanhng224.app.R
import com.thanhng224.app.core.ui.dialog.BaseDialog
import com.thanhng224.app.core.ui.dialog.SlideVerticalEnter
import com.thanhng224.app.core.ui.dialog.SlideVerticalExit
import com.thanhng224.app.presentation.viewmodel.SettingsUiState
import com.thanhng224.app.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onLogout: () -> Unit = {},
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    uiState.snackbarMessageRes?.let { resId ->
        LaunchedEffect(resId) {
            snackbarHostState.showSnackbar(context.getString(resId))
            viewModel.onSnackbarShown()
        }
    }

    LaunchedEffect(uiState.logoutRequested) {
        if (uiState.logoutRequested) {
            onLogout()
            viewModel.onLogoutHandled()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            SettingsContent(
                uiState = uiState,
                onNotificationsToggled = viewModel::onNotificationsToggled,
                onDarkModeToggled = viewModel::onDarkModeToggled,
                onShowTerms = viewModel::onShowTermsDialog,
                onShowPrivacy = viewModel::onShowPrivacyDialog,
                onLogoutClick = viewModel::onLogoutDialogShown
            )
        }
    }

    TermsDialog(
        visible = uiState.showTermsDialog,
        onDismiss = viewModel::onDismissTermsDialog
    )

    PrivacyDialog(
        visible = uiState.showPrivacyDialog,
        onDismiss = viewModel::onDismissPrivacyDialog
    )

    if (uiState.showLogoutDialog) {
        LogoutDialog(
            onConfirm = viewModel::onLogoutConfirmed,
            onDismiss = viewModel::onLogoutDialogDismissed
        )
    }
}

@Composable
private fun SettingsContent(
    uiState: SettingsUiState,
    onNotificationsToggled: (Boolean) -> Unit,
    onDarkModeToggled: (Boolean) -> Unit,
    onShowTerms: () -> Unit,
    onShowPrivacy: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        SettingsHeader()
        ProfileSection()
        PreferencesSection(
            notificationsEnabled = uiState.notificationsEnabled,
            darkModeEnabled = uiState.darkModeEnabled,
            onNotificationsToggled = onNotificationsToggled,
            onDarkModeToggled = onDarkModeToggled
        )
        AccountSection()
        AboutSection(
            onShowTerms = onShowTerms,
            onShowPrivacy = onShowPrivacy
        )
        LogoutSection(onLogoutClick = onLogoutClick)
    }
}

@Composable
private fun SettingsHeader() {
    Text(
        text = stringResource(id = R.string.settings_title),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
private fun ProfileSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.settings_profile_name),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(id = R.string.settings_profile_email),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
private fun PreferencesSection(
    notificationsEnabled: Boolean,
    darkModeEnabled: Boolean,
    onNotificationsToggled: (Boolean) -> Unit,
    onDarkModeToggled: (Boolean) -> Unit
) {
    SettingsSectionTitle(title = stringResource(id = R.string.settings_preferences_title))

    SettingsCard {
        SettingsToggleItem(
            icon = Icons.Default.Notifications,
            title = stringResource(id = R.string.settings_notifications_title),
            subtitle = stringResource(id = R.string.settings_notifications_subtitle),
            checked = notificationsEnabled,
            onCheckedChange = onNotificationsToggled
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        SettingsToggleItem(
            icon = Icons.Default.DarkMode,
            title = stringResource(id = R.string.settings_dark_mode_title),
            subtitle = stringResource(id = R.string.settings_dark_mode_subtitle),
            checked = darkModeEnabled,
            onCheckedChange = onDarkModeToggled
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        SettingsNavigationItem(
            icon = Icons.Default.Language,
            title = stringResource(id = R.string.settings_language_title),
            subtitle = stringResource(id = R.string.settings_language_subtitle),
            onClick = { }
        )
    }
}

@Composable
private fun AccountSection() {
    SettingsSectionTitle(title = stringResource(id = R.string.settings_account_title))

    SettingsCard {
        SettingsNavigationItem(
            icon = Icons.Default.Person,
            title = stringResource(id = R.string.settings_edit_profile_title),
            subtitle = stringResource(id = R.string.settings_edit_profile_subtitle),
            onClick = { }
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        SettingsNavigationItem(
            icon = Icons.Default.Lock,
            title = stringResource(id = R.string.settings_change_password_title),
            subtitle = stringResource(id = R.string.settings_change_password_subtitle),
            onClick = { }
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        SettingsNavigationItem(
            icon = Icons.Default.Security,
            title = stringResource(id = R.string.settings_privacy_security_title),
            subtitle = stringResource(id = R.string.settings_privacy_security_subtitle),
            onClick = { }
        )
    }
}

@Composable
private fun AboutSection(
    onShowTerms: () -> Unit,
    onShowPrivacy: () -> Unit
) {
    SettingsSectionTitle(title = stringResource(id = R.string.settings_about_title))

    SettingsCard {
        SettingsNavigationItem(
            icon = Icons.Default.PrivacyTip,
            title = stringResource(id = R.string.settings_terms_title),
            subtitle = stringResource(id = R.string.settings_terms_subtitle),
            onClick = onShowTerms
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        SettingsNavigationItem(
            icon = Icons.Default.PrivacyTip,
            title = stringResource(id = R.string.settings_privacy_policy_title),
            subtitle = stringResource(id = R.string.settings_privacy_policy_subtitle),
            onClick = onShowPrivacy
        )
    }
}

@Composable
private fun LogoutSection(onLogoutClick: () -> Unit) {
    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = onLogoutClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.settings_logout_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = stringResource(id = R.string.settings_version_label),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
        modifier = Modifier.fillMaxWidth(),
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
    )

    Spacer(modifier = Modifier.height(80.dp))
}

@Composable
private fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
    )
}

@Composable
private fun SettingsCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        content()
    }
}

@Composable
private fun SettingsNavigationItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun SettingsToggleItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}

@Composable
private fun TermsDialog(
    visible: Boolean,
    onDismiss: () -> Unit
) {
    BaseDialog(
        visible = visible,
        onDismiss = onDismiss,
        title = stringResource(id = R.string.settings_terms_title),
        message = stringResource(id = R.string.settings_terms_dialog_message),
        primaryActionText = stringResource(id = R.string.settings_close_action),
        onPrimaryAction = onDismiss
    )
}

@Composable
private fun PrivacyDialog(
    visible: Boolean,
    onDismiss: () -> Unit
) {
    BaseDialog(
        visible = visible,
        onDismiss = onDismiss,
        title = stringResource(id = R.string.settings_privacy_policy_title),
        message = stringResource(id = R.string.settings_privacy_dialog_message),
        primaryActionText = stringResource(id = R.string.settings_close_action),
        onPrimaryAction = onDismiss,
        enter = SlideVerticalEnter,
        exit = SlideVerticalExit,
        dismissOnClickOutside = false
    )
}

@Composable
private fun LogoutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.settings_logout_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.settings_logout_dialog_message),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(stringResource(id = R.string.settings_logout_title))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.settings_cancel_action))
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}
