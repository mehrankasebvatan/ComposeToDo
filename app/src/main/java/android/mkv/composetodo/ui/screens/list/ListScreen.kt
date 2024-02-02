package android.mkv.composetodo.ui.screens.list

import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.SearchTopBarState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()

    val searchTopBarState: SearchTopBarState
            by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    ComposeToDoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(vertical = paddingValues.calculateTopPadding())
                            .fillMaxSize()
                    ) {
                        ListContent(
                            allTasks,
                            navigateToTaskScreen
                        )
                    }
                },
                topBar = {
                    ListTopBar(sharedViewModel, searchTopBarState, searchTextState)
                },
                bottomBar = {},
                floatingActionButton = {
                    ListFab(onFabClicked = navigateToTaskScreen)
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




