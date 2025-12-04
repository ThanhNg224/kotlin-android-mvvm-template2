package com.thanhng224.app.core.ui.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * Simple animated dialog container for Compose screens. Keep state hoisted and
 * avoid data/domain imports to respect the clean architecture boundaries.
 */
@Composable
fun BaseDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    title: String? = null,
    message: String? = null,
    primaryActionText: String? = null,
    onPrimaryAction: (() -> Unit)? = null,
    secondaryActionText: String? = null,
    onSecondaryAction: (() -> Unit)? = null,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    enter: EnterTransition = fadeIn() + scaleIn(),
    exit: ExitTransition = fadeOut() + scaleOut()
) {
    if (!visible) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f))
        ) {
            if (dismissOnClickOutside) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures { onDismiss() }
                        }
                )
            }

            AnimatedVisibility(
                visible = true,
                enter = enter,
                exit = exit
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = modifier
                            .fillMaxWidth(0.9f),
                        shape = RoundedCornerShape(20.dp),
                        tonalElevation = 6.dp,
                        shadowElevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            icon?.let {
                                androidx.compose.material3.Icon(
                                    imageVector = it,
                                    contentDescription = null,
                                    tint = iconTint
                                )
                            }

                            title?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            message?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                if (secondaryActionText != null && onSecondaryAction != null) {
                                    OutlinedButton(
                                        modifier = Modifier.weight(1f),
                                        onClick = onSecondaryAction
                                    ) {
                                        Text(secondaryActionText)
                                    }
                                }

                                if (primaryActionText != null && onPrimaryAction != null) {
                                    Button(
                                        modifier = Modifier.weight(1f),
                                        onClick = onPrimaryAction
                                    ) {
                                        Text(primaryActionText)
                                    }
                                } else {
                                    // keep layout balanced when only one button is present
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FailureDialog(
    visible: Boolean,
    title: String = "Something went wrong",
    message: String? = null,
    onDismiss: () -> Unit,
    primaryActionText: String = "OK",
    onPrimaryAction: (() -> Unit)? = null,
) {
    BaseDialog(
        visible = visible,
        onDismiss = onDismiss,
        icon = Icons.Filled.ErrorOutline,
        iconTint = MaterialTheme.colorScheme.error,
        title = title,
        message = message,
        primaryActionText = primaryActionText,
        onPrimaryAction = onPrimaryAction ?: onDismiss
    )
}

// Convenience slide variant
val SlideVerticalEnter: EnterTransition = slideInVertically(initialOffsetY = { it / 3 }) + fadeIn()
val SlideVerticalExit: ExitTransition = slideOutVertically(targetOffsetY = { it / 3 }) + fadeOut()
