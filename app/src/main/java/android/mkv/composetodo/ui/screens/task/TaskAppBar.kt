package android.mkv.composetodo.ui.screens.task

import android.mkv.composetodo.R
import android.mkv.composetodo.components.DisplayAlertDialog
import android.mkv.composetodo.data.models.ToDoTask
import android.mkv.composetodo.ui.theme.Primary
import android.mkv.composetodo.util.Action
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskAppBar(selectedTask: ToDoTask?, navigateToListScreen: (Action) -> Unit) {
    if (selectedTask == null) NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    else EditTaskAppBar(selectedTask, navigateToListScreen = navigateToListScreen)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,
            titleContentColor = Color.White
        ),
        title = {
            Text(text = stringResource(R.string.add_task))
        },
        navigationIcon = {
            BackAction(navigateToListScreen)
        },
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,
            titleContentColor = Color.White
        ),
        title = {
            Text(
                text = selectedTask.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            CloseAction(navigateToListScreen)
        },
        actions = {
            EditTopBarActions(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        }
    )
}

@Composable
fun EditTopBarActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit,
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, selectedTask.title),
        message = stringResource(id = R.string.delete_task_confirmation, selectedTask.title),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = {
            navigateToListScreen(Action.DELETE)
        })

    DeleteAction(onDeleteClicked = {
        openDialog = true
    })
    UpdateAction(navigateToListScreen)
}


@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onAddClicked(Action.ADD)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "",
            tint = Color.White
        )
    }

}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onUpdateClicked(Action.UPDATE)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "",
            tint = Color.White
        )
    }

}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onDeleteClicked(Action.DELETE)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "",
            tint = Color.White
        )
    }

}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onBackClicked(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.rotate(180f)
        )
    }

}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {
            onCloseClicked(Action.NO_ACTION)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "",
            tint = Color.White
        )
    }

}


@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewNewTaskAppBar() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewEditTaskAppBar() {
    EditTaskAppBar(
        ToDoTask(title = "Title"),
        navigateToListScreen = {})
}