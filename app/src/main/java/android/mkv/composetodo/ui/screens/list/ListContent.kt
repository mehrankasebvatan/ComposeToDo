package android.mkv.composetodo.ui.screens.list

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.R
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.data.models.ToDoTask
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.theme.HighPriorityColor
import android.mkv.composetodo.ui.theme.Typography
import android.mkv.composetodo.util.Action
import android.mkv.composetodo.util.RequestState
import android.mkv.composetodo.util.SearchTopBarState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchTopBarState: SearchTopBarState,
    navigationToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit

) {

    if (sortState is RequestState.Success) {

        when {
            searchTopBarState == SearchTopBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        allTasks = searchedTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigationToTaskScreen = navigationToTaskScreen
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        allTasks = allTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigationToTaskScreen = navigationToTaskScreen
                    )
                }

            }

            sortState.data == Priority.LOW -> {
                HandleListContent(
                    allTasks = lowPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigationToTaskScreen = navigationToTaskScreen
                )
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    allTasks = highPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigationToTaskScreen = navigationToTaskScreen
                )
            }


        }

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleListContent(
    allTasks: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigationToTaskScreen: (taskId: Int) -> Unit
) {
    if (allTasks.isEmpty()) {
        EmptyContent()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = allTasks,
                key = { task ->
                    task.id
                }
            ) { task ->

                ListItem(headlineContent = {

                    val dismissState = rememberDismissState()

                    val dismissDirection = dismissState.dismissDirection
                    val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

                    if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                        val scope = rememberCoroutineScope()
                        scope.launch {
                            delay(300)
                            onSwipeToDelete(Action.DELETE, task)
                        }
                    }

                    val degree by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0f else 45f,
                    )

                    var itemAppeared by remember {
                        mutableStateOf(false)
                    }

                    LaunchedEffect(key1 = true) {
                        itemAppeared = true
                    }

                    AnimatedVisibility(
                        visible = itemAppeared && !isDismissed,
                        enter = expandVertically(
                            animationSpec = tween(
                                durationMillis = 300
                            )
                        ),
                        exit = shrinkHorizontally(
                            animationSpec = tween(
                                durationMillis = 300
                            )
                        )
                    ) {
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                RedBackground(degree = degree)
                            },
                            dismissContent = {
                                TaskItem(
                                    toDoTask = task,
                                    navigationToTaskScreen = navigationToTaskScreen
                                )
                            },

                            )
                    }
                })
            }
        }
    }
}


@Preview(showBackground = true, locale = "fa")
@Composable
fun RedBackground(degree: Float = 0f) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .rotate(degree)
                    .size(50.dp),
                imageVector = Icons.Filled.Delete,
                contentDescription = "",
                tint = HighPriorityColor,
            )
            Text(
                text = stringResource(id = R.string.delete_),
                color = HighPriorityColor,
                textAlign = TextAlign.Center
            )
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

@Preview(showBackground = true, locale = "fa")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, locale = "fa")
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