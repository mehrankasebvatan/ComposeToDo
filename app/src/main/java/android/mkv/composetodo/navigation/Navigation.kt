package android.mkv.composetodo.navigation

import android.mkv.composetodo.navigation.destinations.listComposable
import android.mkv.composetodo.navigation.destinations.splashComposable
import android.mkv.composetodo.navigation.destinations.taskComposable
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.Constant.SPLASH_SCREEN
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavigation(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController)
    }



    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(navigateToListScreen = screen.splash)
        listComposable(navigateToTaskScreen = screen.list, sharedViewModel = viewModel)
        taskComposable(navigateToListScreen = screen.task, sharedViewModel = viewModel)
    }
}