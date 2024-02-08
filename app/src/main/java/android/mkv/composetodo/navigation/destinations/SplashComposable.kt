package android.mkv.composetodo.navigation.destinations

import android.mkv.composetodo.ui.screens.splash.SplashScreen
import android.mkv.composetodo.util.Constant.SPLASH_SCREEN
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN
    ) {
        SplashScreen(navigateToListScreen)
    }
}