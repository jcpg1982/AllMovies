package com.master.machines.allMovies.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.master.machines.allMovies.framework.helpers.Constants
import com.master.machines.allMovies.presentation.theme.ColorWhite
import com.master.machines.allMovies.presentation.theme.Purple80
import com.master.machines.allMovies.presentation.theme.contentInset
import com.master.machines.allMovies.presentation.theme.contentInsetEight
import com.master.machines.allMovies.presentation.theme.contentInsetOneHundredFifty
import com.master.machines.allMovies.presentation.theme.contentInsetQuarter
import com.master.machines.allMovies.presentation.theme.dynamicDisplayTextSize
import com.master.machines.allMovies.presentation.theme.dynamicTwentyFourTextSize
import com.master.machines.allMovies.presentation.ui.components.ImageSubComposeAsyncImage
import com.master.machines.allMovies.presentation.ui.components.LoadingData
import com.master.machines.allMovies.presentation.ui.dialog.DialogAlert
import com.master.machines.allMovies.presentation.viewModels.DetailViewModel

@Composable
fun DetailMovie(movieId: Int) {

    val detailViewModel: DetailViewModel = hiltViewModel()
    val messageLoading by detailViewModel.messageLoading.collectAsState()
    val messageError by detailViewModel.messageError.collectAsState()
    val movieWithGenreId by detailViewModel.movieWithGenreId.collectAsState()
    LaunchedEffect(movieId) {
        detailViewModel.getItemMovies(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageSubComposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            imageUrl = "${Constants.URL_IMAGE}${movieWithGenreId.movie.posterPath}",
            contentScale = ContentScale.FillBounds,
            description = movieWithGenreId.movie.title
        )

        Text(
            text = "TITULO: \n${movieWithGenreId.movie.title}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = contentInsetQuarter,
                    horizontal = contentInset
                ),
            fontSize = dynamicTwentyFourTextSize,
            color = Purple80,
            textAlign = TextAlign.Start,
            lineHeight = dynamicDisplayTextSize
        )
        Row(
            modifier = Modifier.padding(
                vertical = contentInsetQuarter,
                horizontal = contentInset
            )
        ) {
            Text(
                text = "${movieWithGenreId.movie.releaseDate}",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = contentInsetEight),
                fontSize = dynamicTwentyFourTextSize,
                color = Purple80,
                textAlign = TextAlign.Start,
                lineHeight = dynamicDisplayTextSize
            )
            Text(
                text = "${movieWithGenreId.movie.voteAverage}",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = contentInsetEight),
                fontSize = dynamicTwentyFourTextSize,
                color = Purple80,
                textAlign = TextAlign.Start,
                lineHeight = dynamicDisplayTextSize
            )
        }

        Text(
            text = "RESUMEN: \n${movieWithGenreId.movie.overview}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = contentInsetQuarter,
                    horizontal = contentInset
                ),
            fontSize = dynamicTwentyFourTextSize,
            color = Purple80,
            textAlign = TextAlign.Start,
            lineHeight = dynamicDisplayTextSize
        )
    }

    if (messageLoading.isNotEmpty()) LoadingData(message = messageLoading)
    if (messageError.isNotEmpty()) {
        DialogAlert(title = "Error",
            message = messageError,
            isCancelable = true,
            textPositiveButton = "OK",
            textColorPositiveButton = ColorWhite,
            backgroundColorPositiveButton = Purple80,
            onPositiveCallback = { detailViewModel.updateMessageError() },
            onNegativeCallback = { detailViewModel.updateMessageError() },
            onDismissDialog = { detailViewModel.updateMessageError() })
    }
}