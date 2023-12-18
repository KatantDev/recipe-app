package org.katant.recipe.presentation.components
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val darkColorScheme = darkColors(
    surface = Color(0xFF121212),
    onSecondary = Color.White
)
val lightColorScheme = lightColors()

@Composable
fun CustomTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) darkColorScheme else lightColorScheme
    MaterialTheme(
        colors = colorScheme,
        content = content
    )
}