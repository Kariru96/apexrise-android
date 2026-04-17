package com.apexrise.offline.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = MossGreenDark,
    secondary = MossGreen,
    background = SurfaceDark,
    surface = SurfaceDark,
)

private val LightColors = lightColorScheme(
    primary = MossGreen,
    secondary = MossGreenDark,
    background = SurfaceLight,
    surface = SurfaceLight,
)

@Composable
fun ApexRiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content,
    )
}

