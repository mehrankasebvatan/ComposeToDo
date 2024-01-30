package android.mkv.composetodo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.navigation.SetupNavigation
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Main()
        }
    }
}

@Composable
fun Main() {
    lateinit var navController: NavHostController
    ComposeToDoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            navController = rememberNavController()
            SetupNavigation(navController = navController)
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainPreview() {
    Main()
}