package android.mkv.composetodo.navigation.destinations

import android.mkv.composetodo.util.Action
import android.mkv.composetodo.util.Constant
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = Constant.TASK_SCREEN,
        arguments = listOf(navArgument(Constant.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}