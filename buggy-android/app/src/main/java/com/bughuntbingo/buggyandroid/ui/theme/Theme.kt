package com.bughuntbingo.buggyandroid.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0A1F3D),
    secondary = Color(0xFF1A1A3D),
    background = Color(0xFF0A1F3D),
    surface = Color(0xFF1A1A3D),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFFE5E5E5),
    onSurface = Color(0xFFE5E5E5)
)

@Composable
fun BuggyAndroidTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
