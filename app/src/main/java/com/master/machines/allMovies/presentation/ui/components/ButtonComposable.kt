package com.master.machines.allMovies.presentation.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.master.machines.allMovies.presentation.theme.ColorWhite
import com.master.machines.allMovies.presentation.theme.blue_gray_200
import com.master.machines.allMovies.presentation.theme.blue_gray_300
import com.master.machines.allMovies.presentation.theme.dynamicContentTextSize

@Composable
fun CustomIconButton(
    modifier: Modifier, iconNavigation: ImageVector?, iconNavigationColor: Color?
) {
    iconNavigation?.let { iv ->
        iconNavigationColor?.let { inc ->
            Icon(
                imageVector = iv, contentDescription = "Action bar", modifier = modifier, tint = inc
            )
        }
    }
}

@Composable
fun CustomButton(
    modifier: Modifier,
    enabledButton: Boolean,
    textButton: String,
    containerColor: Color,
    onAcceptButton: () -> Unit
) {
    Button(
        onClick = { onAcceptButton() },
        modifier = modifier,
        enabled = enabledButton,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = ColorWhite,
            disabledContainerColor = blue_gray_200,
            disabledContentColor = blue_gray_300
        )
    ) {
        Text(
            text = textButton, fontSize = dynamicContentTextSize
        )
    }
}