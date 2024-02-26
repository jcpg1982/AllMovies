package com.master.machines.allMovies.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.master.machines.allMovies.R
import com.master.machines.allMovies.presentation.theme.Purple40
import com.master.machines.allMovies.presentation.theme.contentInset
import com.master.machines.allMovies.presentation.theme.contentInsetOneHundredFifty
import com.master.machines.allMovies.presentation.theme.contentInsetQuarter
import com.master.machines.allMovies.presentation.theme.dynamicContentTextSize

@Composable
fun LoadingData(message: String) {
    Dialog(
        onDismissRequest = { }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    color = Purple40,
                    strokeWidth = contentInsetQuarter,
                    modifier = Modifier
                        .size(contentInsetOneHundredFifty)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = "Image Loading",
                    modifier = Modifier
                        .size(contentInsetOneHundredFifty)
                        .align(Alignment.Center),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = contentInset, end = contentInset, top = contentInset)
                    .align(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = dynamicContentTextSize,
                textAlign = TextAlign.Center
            )
        }
    }
}