package android.mkv.composetodo.ui.screens.list

import android.mkv.composetodo.R
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.Action
import android.mkv.composetodo.util.SearchTopBarState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val searchTopBarState: SearchTopBarState
            by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val snackbarHostState = remember { SnackbarHostState() }

    //sharedViewModel.handleDatabaseActions(action = action)
    DisplaySnackBar(
        scaffoldState = snackbarHostState,
        handleDatabaseAction = { sharedViewModel.handleDatabaseActions(action = action) },
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    ComposeToDoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                vertical = paddingValues.calculateTopPadding(),
                                horizontal = 0.dp
                            )
                            .padding(vertical = 10.dp)
                    ) {
                        ListContent(
                            allTasks,
                            searchedTasks,
                            lowPriorityTasks,
                            highPriorityTasks,
                            sortState,
                            searchTopBarState,
                            navigateToTaskScreen,
                            onSwipeToDelete = { action, task ->
                                sharedViewModel.action.value = action
                                sharedViewModel.updateTaskFields(task)

                            }
                        )
                    }
                },
                topBar = {
                    ListTopBar(sharedViewModel, searchTopBarState, searchTextState)
                },
                bottomBar = {},
                floatingActionButton = {
                    ListFab(onFabClicked = navigateToTaskScreen)
                },
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                }
            )
        }
    }


}

@Composable
fun ListFab(
    onFabClicked: (Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "",
            tint = Color.White
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplaySnackBar(
    scaffoldState: SnackbarHostState,
    handleDatabaseAction: () -> Unit,
    taskTitle: String,
    onUndoClicked: (Action) -> Unit,
    action: Action
) {
    handleDatabaseAction()
    val undo: String = stringResource(R.string.undo)
    val ok: String = stringResource(R.string.ok)
    val message = stringResource(id = action.title)

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.showSnackbar(
                    message = message,
                    actionLabel = if (action.name == "DELETE") undo else ok

                )
                undoDeleteTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }

}

private fun undoDeleteTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) onUndoClicked(Action.UNDO)
}




