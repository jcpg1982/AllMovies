package com.master.machines.allMovies.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.master.machines.allMovies.presentation.theme.contentInset
import com.master.machines.allMovies.presentation.theme.dynamicTitleTextSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbarLoginCompose(
    title: String?,
    iconNavigation: ImageVector?,
    iconNavigationColor: Color?,
    onClickNavigation: () -> Unit,
    backgroundColor: Color
) {
    TopAppBar(title = {
        iconNavigationColor?.let { inc ->
            Text(
                text = title.orEmpty(),
                modifier = Modifier,
                color = inc,
                fontSize = dynamicTitleTextSize,
                maxLines = 1
            )
        }
    }, navigationIcon = {
        CustomIconButton(
            modifier = Modifier
                .padding(start = contentInset)
                .clickable { onClickNavigation() },
            iconNavigation = iconNavigation,
            iconNavigationColor = iconNavigationColor
        )
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
    )
}