package android.mkv.composetodo.navigation.destinations

import android.mkv.composetodo.ui.screens.task.TaskScreen
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.Action
import android.mkv.composetodo.util.Constant
import android.mkv.composetodo.util.Constant.TASK_ARGUMENT_KEY
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = Constant.TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) sharedViewModel.updateTaskFields(selectedTask)

        }

        TaskScreen(selectedTask, sharedViewModel, navigateToListScreen = navigateToListScreen)
    }
}