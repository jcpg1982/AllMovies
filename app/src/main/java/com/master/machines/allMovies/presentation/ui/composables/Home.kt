package com.master.machines.allMovies.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.master.machines.allMovies.data.models.Routes.DetailMovie.Companion.createDetail
import com.master.machines.allMovies.presentation.theme.ColorWhite
import com.master.machines.allMovies.presentation.theme.Purple80
import com.master.machines.allMovies.presentation.theme.contentInsetFifty
import com.master.machines.allMovies.presentation.theme.contentInsetTen
import com.master.machines.allMovies.presentation.theme.contentInsetTwo
import com.master.machines.allMovies.presentation.ui.components.RowPetComposable
import com.master.machines.allMovies.presentation.ui.dialog.DialogAlert
import com.master.machines.allMovies.presentation.viewModels.HomeViewModel
import com.master.machines.allMovies.presentation.viewModels.NavigationViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Home(navigationViewModel: NavigationViewModel) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val messageLoading by homeViewModel.messageLoading.collectAsState()
    val messageError by homeViewModel.messageError.collectAsState()
    val dataList by homeViewModel.dataList.collectAsState()

    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(contentInsetTen)
        ) {
            items(dataList) { item ->
                RowPetComposable(movie = item, onDetailClick = {
                    item.id?.let { id -> navigationViewModel.onNavigateTo(id.createDetail(item.title.orEmpty())) }
                }, onImageClick = {})
            }
            if (messageLoading.isNotEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(contentInsetTen),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(contentInsetFifty),
                            strokeWidth = contentInsetTwo
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collectLatest { index ->
            if (messageLoading.isEmpty() && index != null && index >= dataList.size - 1) {
                homeViewModel.page++
                homeViewModel.getAllMoviesCloud()
            }
        }
    }

    if (messageError.isNotEmpty()) {
        DialogAlert(
            title = "Error",
            message = messageError,
            isCancelable = true,
            textPositiveButton = "OK",
            textColorPositiveButton = ColorWhite,
            backgroundColorPositiveButton = Purple80,
            onPositiveCallback = { homeViewModel.updateMessageError() },
            onNegativeCallback = { homeViewModel.updateMessageError() },
            onDismissDialog = { homeViewModel.updateMessageError() })
    }
}