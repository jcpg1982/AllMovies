package com.master.machines.allMovies.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.master.machines.allMovies.data.models.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val backStack = Stack<Routes>()

    val onBack: Boolean
        get() {
            return if (backStack.isNotEmpty()) {
                _viewDialogExit.value = false
                _currentRoute.value = backStack.pop()
                false
            } else {
                val isFirstBackPress = !_viewDialogExit.value
                _viewDialogExit.value = true
                isFirstBackPress
            }
        }

    private val _currentRoute: MutableStateFlow<Routes> = MutableStateFlow(Routes.Login)
    val currentRoute: StateFlow<Routes> = _currentRoute
    private val _viewDialogExit: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val viewDialogExit: StateFlow<Boolean> = _viewDialogExit

    fun onNavigateTo(route: Routes) {
        viewModelScope.launch {
            backStack.push(currentRoute.value)
            _currentRoute.value = route
        }
    }
}