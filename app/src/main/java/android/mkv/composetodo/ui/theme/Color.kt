package android.mkv.composetodo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Primary = Color(0xFF638889)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFfcfcfc)
val MediumGray = Color(0xFF9c9c9c)
val DarkGray = Color(0xFF141414)


val LowPriorityColor = Color(0xFF00c980)
val MediumPriorityColor = Color(0xFFffc114)
val HighPriorityColor = Color(0xFFff4646)
val NonePriorityColor = Color(0xFFffffff)

val ColorScheme.TopAppBarContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.White


val ColorScheme.TaskBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else Color.White