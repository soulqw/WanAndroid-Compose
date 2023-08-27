import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

object GlobalComposeTheme {

    val colors: WanAndroidColors
        @Composable
        get() = LocalWeComposeColors.current

    enum class Theme {
        Light, Dark
    }
}

@Composable
fun WanAndroidTheme(
    theme: GlobalComposeTheme.Theme = GlobalComposeTheme.Theme.Light,
    content: @Composable () -> Unit
) {
    val targetColors = when (theme) {
        GlobalComposeTheme.Theme.Light -> LightColor
        GlobalComposeTheme.Theme.Dark -> DarkColor
    }
    val background = animateColorAsState(targetColors.background, TweenSpec(600))
    val divider = animateColorAsState(targetColors.divider, TweenSpec(600))
    val primary = animateColorAsState(targetColors.primary, TweenSpec(600))
    val primaryText = animateColorAsState(targetColors.primaryText, TweenSpec(600))
    val secondary = animateColorAsState(targetColors.secondary, TweenSpec(600))

    val colorsFinal = WanAndroidColors(
        background = background.value,
        divider = divider.value,
        primary = primary.value,
        primaryText = primaryText.value,
        secondary = secondary.value
    )
    CompositionLocalProvider(LocalWeComposeColors provides colorsFinal) {
        MaterialTheme(content = content)
    }
}

private val LightColor = WanAndroidColors(
    background = Color.White,
    divider = Color.LightGray,
    primary = Color(0xFF6200EE),
    primaryText = Color.Black,
    secondary = Color.Gray
)

private val DarkColor = WanAndroidColors(
    background = Color.Black,
    divider = Color.LightGray,
    primary = Color(0xFF6200EE),
    primaryText = Color.White,
    secondary = Color(0xFFBDBDBD)
)

private val LocalWeComposeColors = compositionLocalOf {
    LightColor
}

@Stable
class WanAndroidColors(
    background: Color,
    divider: Color,
    primary: Color,
    primaryText: Color,
    secondary: Color,
) {
    var background: Color by mutableStateOf(background)

    var divider: Color by mutableStateOf(divider)

    var primary: Color by mutableStateOf(primary)

    var primaryText: Color by mutableStateOf(primaryText)

    var secondary: Color by mutableStateOf(secondary)

}