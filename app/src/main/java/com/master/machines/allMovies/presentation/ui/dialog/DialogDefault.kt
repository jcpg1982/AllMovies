package com.master.machines.allMovies.presentation.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.master.machines.allMovies.data.enums.DialogType
import com.master.machines.allMovies.presentation.theme.ColorWhite
import com.master.machines.allMovies.presentation.theme.contentInset
import com.master.machines.allMovies.presentation.ui.components.CustomText
import com.master.machines.allMovies.presentation.ui.components.MessageDialog
import com.master.machines.allMovies.presentation.ui.components.TextClickButton


@Composable
fun CustomDefaultDialog(
    title: String? = null,
    message: String? = null,
    dialogType: DialogType,
    isCancelable: Boolean = false,
    textPositiveButton: String? = null,
    textColorPositiveButton: Color? = null,
    backgroundColorPositiveButton: Color? = null,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String? = null,
    textColorNegativeButton: Color? = null,
    backgroundColorNegativeButton: Color? = null,
    onNegativeCallback: () -> Unit,
    onDismissDialog: () -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Dialog(
        onDismissRequest = { onDismissDialog() }, properties = DialogProperties(
            dismissOnBackPress = isCancelable, dismissOnClickOutside = isCancelable
        )
    ) {
        OutlinedCard(
            modifier = Modifier.widthIn(min = screenWidth * 0.20f, max = screenWidth * 0.75f)
        ) {
            when (dialogType) {
                DialogType.ALERT -> {
                    title?.let { t ->
                        message?.let { m ->
                            textPositiveButton?.let { tpb ->
                                textColorPositiveButton?.let { tcpb ->
                                    backgroundColorPositiveButton?.let { bcpb ->
                                        Alert(
                                            title = t,
                                            message = m,
                                            textPositiveButton = tpb,
                                            textColorPositiveButton = tcpb,
                                            backgroundColorPositiveButton = bcpb
                                        ) { onPositiveCallback() }
                                    }
                                }
                            }
                        }
                    }
                }

                DialogType.CONFIRM -> {
                    title?.let { t ->
                        message?.let { m ->
                            textPositiveButton?.let { tpb ->
                                textColorPositiveButton?.let { tcpb ->
                                    backgroundColorPositiveButton?.let { bcpb ->
                                        textNegativeButton?.let { tnb ->
                                            textColorNegativeButton?.let { tcnb ->
                                                backgroundColorNegativeButton?.let { bcnb ->
                                                    Confirm(title = t,
                                                        message = m,
                                                        textPositiveButton = tpb,
                                                        textColorPositiveButton = tcpb,
                                                        backgroundColorPositiveButton = bcpb,
                                                        onPositiveCallback = { onPositiveCallback() },
                                                        textNegativeButton = tnb,
                                                        textColorNegativeButton = tcnb,
                                                        backgroundColorNegativeButton = bcnb,
                                                        onNegativeCallback = { onNegativeCallback() })
                                                }
                                            }
                                        }
                                    }
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
fun Alert(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(contentInset)
    ) {
        CustomText(title = title)
        MessageDialog(message = message)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = contentInset),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textPositiveButton,
                textColorButton = textColorPositiveButton,
                backgroundColorButton = backgroundColorPositiveButton
            ) { onPositiveCallback() }
        }
    }
}

@Composable
fun Confirm(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String,
    textColorNegativeButton: Color,
    backgroundColorNegativeButton: Color,
    onNegativeCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(contentInset)
    ) {
        CustomText(title = title)
        MessageDialog(message = message)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = contentInset),
            horizontalArrangement = Arrangement.End
        ) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textNegativeButton,
                textColorButton = textColorNegativeButton,
                backgroundColorButton = backgroundColorNegativeButton
            ) { onNegativeCallback() }
            Spacer(modifier = Modifier.width(contentInset))
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textPositiveButton,
                textColorButton = textColorPositiveButton,
                backgroundColorButton = backgroundColorPositiveButton
            ) { onPositiveCallback() }
        }
    }
}