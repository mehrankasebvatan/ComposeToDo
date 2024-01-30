package android.mkv.composetodo.navigation

import android.mkv.composetodo.util.Action
import android.mkv.composetodo.util.Constant.LIST_SCREEN
import androidx.navigation.NavHostController

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }

}