package android.mkv.composetodo.ui.screens.task

import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.data.models.ToDoTask
import android.mkv.composetodo.util.Action
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                TaskContent(
                    title = selectedTask?.title ?: "",
                    onTitleChange = {},
                    description = selectedTask?.description ?: "",
                    onDescriptionChange = {},
                    priority = selectedTask?.priority ?: Priority.LOW,
                    onPriorityChange = {}
                )
            }
        },
        topBar = {
            TaskAppBar(selectedTask, navigateToListScreen)
        }
    )
}


@Preview(showSystemUi = true)
@Composable
fun PreviewTaskScreen() {
    TaskScreen(ToDoTask(), {})
}