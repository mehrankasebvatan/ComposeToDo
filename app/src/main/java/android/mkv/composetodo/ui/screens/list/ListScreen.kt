package android.mkv.composetodo.ui.screens.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit
) {
    ComposeToDoTheme {
        Surface {
            Scaffold(
                content = { paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues)
                    ) {

                    }
                },
                topBar = {
                    ListTopBar()
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

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}