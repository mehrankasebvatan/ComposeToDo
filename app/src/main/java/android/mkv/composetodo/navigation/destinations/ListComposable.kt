package android.mkv.composetodo.navigation.destinations

import android.mkv.composetodo.ui.screens.list.ListScreen
import android.mkv.composetodo.util.Constant.LIST_ARGUMENT_KEY
import android.mkv.composetodo.util.Constant.LIST_SCREEN
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(navigateToTaskScreen)
    }
}