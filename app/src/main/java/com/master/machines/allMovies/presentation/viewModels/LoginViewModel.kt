package com.master.machines.allMovies.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.master.machines.allMovies.data.models.User
import com.master.machines.allMovies.framework.helpers.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user: StateFlow<User> = _user
    private val _enabledBottom: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val enabledBottom: StateFlow<Boolean> = _enabledBottom
    private val _messageLoading: MutableStateFlow<String> = MutableStateFlow("")
    val messageLoading: StateFlow<String> = _messageLoading
    private val _messageError: MutableStateFlow<String> = MutableStateFlow("")
    val messageError: StateFlow<String> = _messageError
    private val _loginSuccessful: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginSuccessful: StateFlow<Boolean> = _loginSuccessful

    private val validateLogin: Boolean
        get() = user.value.user == Constants.Credentials.USERNAME && user.value.password == Constants.Credentials.PASSWORD

    fun updateMessageError() {
        _messageError.value = ""
    }

    fun updateLoginSuccessful() {
        _loginSuccessful.value = false
    }

    fun updateUserAndPassword(user: String, password: String) {
        _user.update { u ->
            u.copy(user = user, password = password)
        }
        _enabledBottom.value =
            !this.user.value.user.isNullOrEmpty() && !this.user.value.password.isNullOrEmpty()
    }

    fun login() {
        viewModelScope.launch {
            _messageLoading.value = "Iniciando sesión"
            delay(2000)
            val (loading, error) = if (validateLogin) {
                _loginSuccessful.value = true
                Pair("", "")
            } else Pair("", "Verifique la contraseña")
            _messageError.value = error
            _messageLoading.value = loading
        }
    }
}