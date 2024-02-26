package com.master.machines.allMovies.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.master.machines.allMovies.data.models.Movie
import com.master.machines.allMovies.framework.helpers.Constants
import com.master.machines.allMovies.presentation.theme.Purple80
import com.master.machines.allMovies.presentation.theme.blue_gray_100
import com.master.machines.allMovies.presentation.theme.blue_gray_300
import com.master.machines.allMovies.presentation.theme.blue_gray_600
import com.master.machines.allMovies.presentation.theme.contentInsetEight
import com.master.machines.allMovies.presentation.theme.contentInsetFiftySix
import com.master.machines.allMovies.presentation.theme.contentInsetFive
import com.master.machines.allMovies.presentation.theme.contentInsetOne
import com.master.machines.allMovies.presentation.theme.dynamicMiniTextSize
import com.master.machines.allMovies.presentation.theme.dynamicTitleTextSize

@Composable
fun RowPetComposable(
    movie: Movie,
    onDetailClick: () -> Unit,
    onImageClick: () -> Unit,
) {
    val rounded = RoundedCornerShape(contentInsetFive)
    OutlinedCard(border = BorderStroke(contentInsetOne, blue_gray_300),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = contentInsetEight, end = contentInsetEight
            )
            .background(color = Color.Transparent)
            .clip(rounded),
        onClick = { onDetailClick() }) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            //image
            ImageSubComposeAsyncImage(modifier = Modifier
                .clickable { onImageClick() }
                .padding(
                    start = contentInsetEight, top = contentInsetEight, bottom = contentInsetEight
                )
                .size(contentInsetFiftySix)
                .clip(CircleShape)
                .border(contentInsetOne, blue_gray_100, CircleShape),
                imageUrl = "${Constants.URL_IMAGE}${movie.poster_path}",
                contentScale = ContentScale.Crop,
                description = movie.title)

            Column(
                modifier = Modifier
                    .padding(
                        start = contentInsetEight,
                        end = contentInsetEight,
                        top = contentInsetEight,
                        bottom = contentInsetEight
                    )
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    //title
                    Text(
                        modifier = Modifier.weight(1f),
                        text = movie.title.orEmpty(),
                        fontSize = dynamicTitleTextSize,
                        textAlign = TextAlign.Start,
                        color = Purple80,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                }

                //description
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = contentInsetEight),
                    text = if (movie.overview.isNullOrEmpty()) "Not description" else movie.overview.orEmpty(),
                    fontSize = dynamicMiniTextSize,
                    textAlign = TextAlign.Justify,
                    color = blue_gray_600,
                    minLines = 1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}