package com.master.machines.allMovies.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.master.machines.allMovies.R
import com.master.machines.allMovies.data.models.Routes
import com.master.machines.allMovies.framework.helpers.Constants
import com.master.machines.allMovies.presentation.theme.ColorWhite
import com.master.machines.allMovies.presentation.theme.Purple80
import com.master.machines.allMovies.presentation.theme.contentInset
import com.master.machines.allMovies.presentation.theme.contentInsetThirtyTwo
import com.master.machines.allMovies.presentation.ui.components.CustomButton
import com.master.machines.allMovies.presentation.ui.components.CustomTextInput
import com.master.machines.allMovies.presentation.ui.components.CustomTextPassword
import com.master.machines.allMovies.presentation.ui.components.LoadingData
import com.master.machines.allMovies.presentation.ui.dialog.DialogAlert
import com.master.machines.allMovies.presentation.viewModels.LoginViewModel
import com.master.machines.allMovies.presentation.viewModels.NavigationViewModel

@Composable
fun Login(navigationViewModel: NavigationViewModel) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val user by loginViewModel.user.collectAsState()
    val enabledBottom by loginViewModel.enabledBottom.collectAsState()
    val messageLoading by loginViewModel.messageLoading.collectAsState()
    val messageError by loginViewModel.messageError.collectAsState()
    val loginSuccessful by loginViewModel.loginSuccessful.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "header",
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = contentInset, end = contentInset, top = contentInsetThirtyTwo
                )
                .align(Alignment.CenterHorizontally),
            alignment = Alignment.TopCenter
        )

        CustomTextInput(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = contentInset, end = contentInset, top = contentInset
            ),
            hintText = "Usuario",
            value = user.user.orEmpty(),
            maxCharacter = 10,
            isEnabled = true,
            isReadOnly = false,
            keyboardType = KeyboardType.Text,
            isErrorDisplayed = false,
            messageError = "",
            maxLines = 1,
            regex = Constants.Regex.ONLY_LETTERS,
            onTextValueChange = {
                loginViewModel.updateUserAndPassword(it, user.password.orEmpty())
            },
            onClickTextView = {})

        CustomTextPassword(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = contentInset, end = contentInset, top = contentInset
            ),
            hintPassword = "Contraseña",
            password = user.password.orEmpty(),
            maxCharacter = 20,
            isErrorDisplayed = false,
            messageError = "",
            regex = Constants.Regex.MIXTO,
            onValueChange = {
                loginViewModel.updateUserAndPassword(user.user.orEmpty(), it)
            })

        CustomButton(modifier = Modifier
            .fillMaxWidth()
            .padding(all = contentInset),
            enabledButton = enabledBottom,
            textButton = "Iniciar sesión",
            containerColor = Purple80,
            onAcceptButton = { loginViewModel.login() })
    }

    if (messageLoading.isNotEmpty()) LoadingData(message = messageLoading)
    if (messageError.isNotEmpty()) {
        DialogAlert(
            title = "Error",
            message = messageError,
            isCancelable = true,
            textPositiveButton = "OK",
            textColorPositiveButton = ColorWhite,
            backgroundColorPositiveButton = Purple80,
            onPositiveCallback = { loginViewModel.updateMessageError() },
            onNegativeCallback = { loginViewModel.updateMessageError() },
            onDismissDialog = { loginViewModel.updateMessageError() })
    }
    if (loginSuccessful) {
        loginViewModel.updateLoginSuccessful()
        navigationViewModel.onNavigateTo(Routes.Home)
    }
}