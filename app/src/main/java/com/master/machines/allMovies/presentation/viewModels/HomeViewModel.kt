package com.master.machines.allMovies.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.master.machines.allMovies.data.models.Movie
import com.master.machines.allMovies.data.models.ResponseAllMoviesDTO
import com.master.machines.allMovies.data.models.User
import com.master.machines.allMovies.framework.helpers.Constants
import com.master.machines.allMovies.usesCase.MovieUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUsesCase: MovieUsesCase) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName
    var page = 1
    var totalPages = 1

    private val _messageLoading: MutableStateFlow<String> = MutableStateFlow("")
    val messageLoading: StateFlow<String> = _messageLoading
    private val _messageError: MutableStateFlow<String> = MutableStateFlow("")
    val messageError: StateFlow<String> = _messageError
    private val _dataList: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val dataList: StateFlow<List<Movie>> = _dataList

    fun updateMessageError() {
        _messageError.value = ""
    }

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        if (page <= totalPages) {
            viewModelScope.launch {
                movieUsesCase.invoke(page, Constants.APY_KEY).flowOn(Dispatchers.IO).onStart {
                    _messageLoading.value = "Loading Movies"
                }.catch { e ->
                    _messageLoading.value = ""
                    _messageError.value = e.message.orEmpty()
                }.collect {
                    Log.i(TAG, "getAllMovies page: ${it.body()?.page}")
                    Log.i(TAG, "getAllMovies total_pages: ${it.body()?.total_pages}")
                    Log.i(TAG, "getAllMovies total_results: ${it.body()?.total_results}")
                    totalPages = it.body()?.total_pages ?: 1
                    if (it.isSuccessful) {
                        it.body()?.results?.let { results ->
                            _dataList.update { dl -> dl + results }
                            saveMoviesLocal(results)
                        }
                    } else {
                    }
                    _messageLoading.value = ""
                }
            }
        }
    }

    private fun saveMoviesLocal(listMovies: List<Movie>) {
        viewModelScope.launch {
            movieUsesCase.invoke(listMovies).flowOn(Dispatchers.IO).onStart {}.catch {}.collect {}
        }
    }
}