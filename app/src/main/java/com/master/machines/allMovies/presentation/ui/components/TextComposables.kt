package com.master.machines.allMovies.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.master.machines.allMovies.presentation.theme.ContentInsetThreeHundred
import com.master.machines.allMovies.presentation.theme.Purple80
import com.master.machines.allMovies.presentation.theme.blue_gray_500
import com.master.machines.allMovies.presentation.theme.contentInsetFive
import com.master.machines.allMovies.presentation.theme.contentInsetQuarter
import com.master.machines.allMovies.presentation.theme.dynamicBodyTextSize
import com.master.machines.allMovies.presentation.theme.dynamicContentTextSize
import com.master.machines.allMovies.presentation.theme.dynamicDisplayTextSize
import com.master.machines.allMovies.presentation.theme.dynamicTwentyFourTextSize

@Composable
fun CustomText(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = contentInsetQuarter
            ),
        fontSize = dynamicTwentyFourTextSize,
        color = Purple80,
        textAlign = TextAlign.Start,
        lineHeight = dynamicDisplayTextSize
    )
}

@Composable
fun MessageDialog(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .heightIn(max = ContentInsetThreeHundred)
    ) {
        Text(
            text = message,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = contentInsetQuarter),
            fontSize = dynamicContentTextSize,
            color = blue_gray_500,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun TextClickButton(
    modifier: Modifier,
    textButton: String,
    textColorButton: Color,
    backgroundColorButton: Color,
    onClick: () -> Unit
) {
    val rounded = RoundedCornerShape(contentInsetQuarter)
    TextButton(
        onClick = { onClick() },
        modifier = modifier,
        shape = rounded,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColorButton, contentColor = textColorButton
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = contentInsetFive
        )
    ) {
        Text(
            text = textButton, fontSize = dynamicBodyTextSize, maxLines = 1
        )
    }
}