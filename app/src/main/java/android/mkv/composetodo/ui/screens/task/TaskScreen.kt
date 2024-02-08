package android.mkv.composetodo.ui.screens.task

import android.mkv.composetodo.R
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.data.models.ToDoTask
import android.mkv.composetodo.ui.screens.list.DisplaySnackBar
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.Action
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {

    val message = stringResource(R.string.fields_empty)

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority
    val context = LocalContext.current

    Scaffold(
        content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = {
                        sharedViewModel.updateTitle(it)
                    },
                    description = description,
                    onDescriptionChange = {
                        sharedViewModel.description.value = it
                    },
                    priority = priority,
                    onPriorityChange = {
                        sharedViewModel.priority.value = it
                    }
                )
            }
        },
        topBar = {
            TaskAppBar(selectedTask) { action ->
                if (action == Action.NO_ACTION) navigateToListScreen(action)
                else {
                    if (sharedViewModel.validateFields()) navigateToListScreen(action)
                    else {
                        Toast.makeText(
                            context,
                            message, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    )
}


