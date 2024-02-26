package com.master.machines.allMovies.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.master.machines.allMovies.data.dataBase.pojo.MovieWithGenreId
import com.master.machines.allMovies.usesCase.MovieUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUsesCase: MovieUsesCase) : ViewModel() {
    private val TAG = DetailViewModel::class.java.simpleName
    private val _messageLoading: MutableStateFlow<String> = MutableStateFlow("")
    val messageLoading: StateFlow<String> = _messageLoading
    private val _messageError: MutableStateFlow<String> = MutableStateFlow("")
    val messageError: StateFlow<String> = _messageError
    private val _movieWithGenreId: MutableStateFlow<MovieWithGenreId> =
        MutableStateFlow(MovieWithGenreId())
    val movieWithGenreId: StateFlow<MovieWithGenreId> = _movieWithGenreId

    fun updateMessageError() {
        _messageError.value = ""
    }

    fun getItemMovies(movieId: Int) {
        viewModelScope.launch {
            movieUsesCase.invoke(movieId).flowOn(Dispatchers.IO).onStart {
                _messageLoading.value = "Loading Movies"
            }.catch { e ->
                _messageLoading.value = ""
                _messageError.value = e.message.orEmpty()
            }.collect {
                if (it != null) {
                    _movieWithGenreId.value = it
                } else {
                    _messageError.value = "Movie not found"
                }
                _messageLoading.value = ""
            }
        }
    }
}