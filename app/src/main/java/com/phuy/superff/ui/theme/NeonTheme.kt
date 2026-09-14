package com.phuy.superff.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Cyan = Color(0xFF00F0FF)
val Pink = Color(0xFFFF00E5)
val Purple = Color(0xFFB400FF)

private val DarkScheme = darkColorScheme(
    primary = Cyan,
    secondary = Pink,
    tertiary = Purple,
    background = Color(0xFF0A0A14),
    surface = Color(0xFF12121E)
)

@Composable
fun NeonTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkScheme, content = content)
}
