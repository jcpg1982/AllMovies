package com.master.machines.allMovies.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
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
        getAllMoviesCloud()
    }

    fun getAllMoviesCloud() {
        if (page <= totalPages) {
            viewModelScope.launch {
                movieUsesCase.invoke(page, Constants.APY_KEY).flowOn(Dispatchers.IO).onStart {
                    _messageLoading.value = "Loading Movies"
                }.catch { e ->
                    _messageLoading.value = ""
                    _messageError.value = e.message.orEmpty()
                    getAllMoviesLocal()
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
                        getAllMoviesLocal()
                    }
                    _messageLoading.value = ""
                }
            }
        }
    }

    private fun getAllMoviesLocal() {
        viewModelScope.launch {
            Log.i(TAG, "getAllMoviesLocal init list: ${dataList.value.map { it.id ?: 0 }}")
            movieUsesCase.invoke(dataList.value.map { it.id ?: 0 }).flowOn(Dispatchers.IO).onStart {
                _messageLoading.value = "Loading Movies"
                Log.i(TAG, "getAllMoviesLocal onStart")
            }.catch { e ->
                _messageLoading.value = ""
                _messageError.value = e.message.orEmpty()
                Log.i(TAG, "getAllMoviesLocal catch")
            }.collect {
                Log.i(TAG, "getAllMoviesLocal collect: $it")
                val listMovies = mutableListOf<Movie>()
                Log.i(TAG, "getAllMoviesLocal collect listMovies 1: $listMovies")
                it.map { item ->
                    listMovies.add(Movie(item))
                }
                Log.i(TAG, "getAllMoviesLocal collect listMovies 2: $listMovies")
                _dataList.update { dl -> dl + listMovies }
                _messageLoading.value = ""
            }
        }
    }

    private fun saveMoviesLocal(listMovies: List<Movie>) {
        viewModelScope.launch {
            movieUsesCase.invoke(listMovies, false).flowOn(Dispatchers.IO).onStart {}.catch {}
                .collect {}
        }
    }
}