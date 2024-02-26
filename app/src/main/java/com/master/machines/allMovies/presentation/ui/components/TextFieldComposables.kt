package com.master.machines.allMovies.presentation.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import com.master.machines.allMovies.presentation.theme.dynamicContentTextSize

@Composable
fun CustomTextInput(
    modifier: Modifier,
    hintText: String,
    value: String,
    maxCharacter: Int,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    keyboardType: KeyboardType,
    isErrorDisplayed: Boolean,
    messageError: String,
    maxLines: Int,
    regex: Regex,
    onTextValueChange: (String) -> Unit,
    onClickTextView: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxCharacter && it.matches(regex = regex)) {
                onTextValueChange(it)
            }
        },
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
        label = {
            Text(
                text = hintText,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                maxLines = 1,
                style = TextStyle(fontSize = dynamicContentTextSize)
            )
        },
        supportingText = {
            if (isErrorDisplayed) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = messageError,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if (isErrorDisplayed) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "error",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },
        isError = isErrorDisplayed,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = ImeAction.Done
        ),
        singleLine = maxLines == 1,
        minLines = 1,
        maxLines = maxLines,
        interactionSource = interactionSource
    )

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClickTextView()
            }
        }
    }
}

@Composable
fun CustomTextPassword(
    modifier: Modifier,
    hintPassword: String,
    password: String,
    maxCharacter: Int,
    isErrorDisplayed: Boolean,
    messageError: String,
    regex: Regex,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = {
            if (it.length <= maxCharacter && it.matches(regex = regex)) {
                onValueChange(it)
            }
        },
        modifier = modifier,
        label = {
            Text(
                text = hintPassword, fontSize = dynamicContentTextSize
            )
        },
        supportingText = {
            if (isErrorDisplayed) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = messageError,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                val image = if (passwordVisibility) Icons.Filled.VisibilityOff
                else Icons.Filled.Visibility

                Icon(
                    imageVector = image,
                    contentDescription = "show password",
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation(),
        isError = isErrorDisplayed,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        maxLines = 1,
        singleLine = true
    )
}