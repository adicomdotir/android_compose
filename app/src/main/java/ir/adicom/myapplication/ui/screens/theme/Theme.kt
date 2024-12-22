package ir.adicom.myapplication.ui.screens.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
    primary = Black,
    primaryContainer = Purple700,
    secondary = TransparentDarkBlue,
    onSurface = WhiteTransparent,
    onSecondary = HighTransparentDarkBlue,
    secondaryContainer = LightBlue,
    error = ErrorRed
)

private val LightColorPalette = lightColorScheme(
    primary = Black,
    primaryContainer = Purple700,
    secondary = TransparentDarkBlue,
    onSurface = WhiteTransparent,
    onSecondary = HighTransparentDarkBlue,
    secondaryContainer = LightBlue,
    error = ErrorRed
)

@Composable
fun ComposeWeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
