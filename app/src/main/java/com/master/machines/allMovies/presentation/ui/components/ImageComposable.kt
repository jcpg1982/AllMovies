package com.master.machines.allMovies.presentation.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.master.machines.allMovies.R
import com.master.machines.allMovies.framework.utils.ContextExtension.obtainColor
import com.master.machines.allMovies.framework.utils.TextDrawable

@Composable
fun imageAsync(painter: Painter, description: String) =
    Image(painter = painter, contentDescription = description)

@Composable
fun ImageSubComposeAsyncImage(
    modifier: Modifier, imageUrl: String, contentScale: ContentScale, description: String?
) {
    val resources = LocalContext.current.resources
    val context = LocalContext.current
    val letter = if (description.isNullOrEmpty()) {
        "AM"
    } else {
        val nameSlit = description.trim().split(" ")
        if (nameSlit.size == 1) {
            nameSlit[0].substring(0, 1)
        } else {
            "${nameSlit[0].substring(0, 1)}${nameSlit[1].substring(0, 1)}"
        }
    }
    val placeHolderDrawable: Drawable = TextDrawable(
        context.obtainColor(R.color.blue_gray_200),
        letter,
        context.obtainColor(R.color.color_white),
        resources.getDimension(R.dimen.content_placeholder_principal),
        ResourcesCompat.getFont(context, R.font.poppins_bold)
    )
    val placeHolderPainter = rememberDrawablePainter(placeHolderDrawable)
    SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
        .crossfade(true).build(),
        contentDescription = description,
        modifier = modifier,
        contentScale = contentScale,
        loading = {
            imageAsync(painter = placeHolderPainter, description.orEmpty())
        },
        error = {
            imageAsync(painter = placeHolderPainter, description.orEmpty())
        },
        success = {
            SubcomposeAsyncImageContent()
        })
}
