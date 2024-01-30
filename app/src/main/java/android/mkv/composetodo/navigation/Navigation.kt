package android.mkv.composetodo.navigation

import android.mkv.composetodo.navigation.destinations.listComposable
import android.mkv.composetodo.navigation.destinations.taskComposable
import android.mkv.composetodo.util.Constant.LIST_SCREEN
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController)
    }



    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToListScreen = screen.list)
    }
}