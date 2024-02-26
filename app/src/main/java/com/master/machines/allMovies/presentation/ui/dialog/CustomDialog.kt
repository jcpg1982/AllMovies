package com.master.machines.allMovies.presentation.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.master.machines.allMovies.data.enums.DialogType

@Composable
fun DialogAlert(
    title: String,
    message: String,
    isCancelable: Boolean,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    onNegativeCallback: () -> Unit,
    onDismissDialog: () -> Unit
) {
    CustomDefaultDialog(title = title,
        message = message,
        dialogType = DialogType.ALERT,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = { onPositiveCallback() },
        onNegativeCallback = { onNegativeCallback() },
        onDismissDialog = { onDismissDialog() })
}

@Composable
fun DialogConfirm(
    title: String,
    message: String,
    isCancelable: Boolean,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String,
    textColorNegativeButton: Color,
    backgroundColorNegativeButton: Color,
    onNegativeCallback: () -> Unit,
    onDismissDialog: () -> Unit
) {
    CustomDefaultDialog(title = title,
        message = message,
        dialogType = DialogType.CONFIRM,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = { onPositiveCallback() },
        textNegativeButton = textNegativeButton,
        textColorNegativeButton = textColorNegativeButton,
        backgroundColorNegativeButton = backgroundColorNegativeButton,
        onNegativeCallback = { onNegativeCallback() },
        onDismissDialog = { onDismissDialog() })
}