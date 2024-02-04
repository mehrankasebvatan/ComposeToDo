package android.mkv.composetodo.ui.screens.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.data.models.ToDoTask
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.theme.Typography
import android.mkv.composetodo.util.RequestState
import android.mkv.composetodo.util.SearchTopBarState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    searchTopBarState: SearchTopBarState,
    navigationToTaskScreen: (taskId: Int) -> Unit
) {
    if (searchTopBarState == SearchTopBarState.TRIGGERED) {
        if (searchedTasks is RequestState.Success) {
            HandleListContent(
                allTasks = searchedTasks.data,
                navigationToTaskScreen = navigationToTaskScreen
            )
        }
    } else {
        if (allTasks is RequestState.Success) {
            HandleListContent(
                allTasks = allTasks.data,
                navigationToTaskScreen = navigationToTaskScreen
            )
        }
    }
}

@Composable
fun HandleListContent(
    allTasks: List<ToDoTask>,
    navigationToTaskScreen: (taskId: Int) -> Unit
) {
    if (allTasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(allTasks.size) { task ->
                ListItem(headlineContent = {
                    TaskItem(
                        toDoTask = allTasks[task],
                        navigationToTaskScreen = navigationToTaskScreen
                    )
                })
            }
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigationToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            navigationToTaskScreen(toDoTask.id)
        }

    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(10.dp)

        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = toDoTask.title,
                    modifier = Modifier
                        .weight(8f)
                        .padding(5.dp),
                    style = Typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis

                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(end = 5.dp, top = 4.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Canvas(modifier = Modifier.size(16.dp)) {
                        drawCircle(color = toDoTask.priority.color)
                    }
                }
            }

            Text(
                text = toDoTask.description, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .padding(bottom = 5.dp),
                maxLines = 2,
                style = Typography.bodyLarge,
                overflow = TextOverflow.Ellipsis
            )

        }


    }

}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewTaskItem() {
    ComposeToDoTheme {
        Surface {
            TaskItem(toDoTask = ToDoTask(
                title = "title", description = "description", priority = Priority.HIGH

            ), navigationToTaskScreen = {})
        }
    }

}