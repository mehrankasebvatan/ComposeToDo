package android.mkv.composetodo.navigation.destinations

import android.mkv.composetodo.ui.screens.list.ListScreen
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.Constant.LIST_ARGUMENT_KEY
import android.mkv.composetodo.util.Constant.LIST_SCREEN
import android.mkv.composetodo.util.toAction
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStateEntry ->
        val action = navBackStateEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

        ListScreen(navigateToTaskScreen, sharedViewModel = sharedViewModel)
    }
}