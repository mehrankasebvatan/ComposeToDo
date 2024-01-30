package android.mkv.composetodo.ui.screens.list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.theme.TopAppBarContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListTopBar() {
    DefaultListTopBar()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colorScheme.TopAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            SearchAction {

            }
            SortAction {

            }
            MenuAction {

            }
        }
    )
}

@Composable
fun ListTopBarActions() {

}

@Composable
fun SearchAction(
    onSearchAction: () -> Unit
) {

    IconButton(onClick = {
        onSearchAction()
    }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.TopAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortAction: (Priority) -> Unit
) {

    IconButton(onClick = {
        onSortAction(Priority.NONE)
    }) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.TopAppBarContentColor
        )
    }
}

@Composable
fun MenuAction(
    onSearchAction: () -> Unit
) {

    IconButton(onClick = {
        onSearchAction()
    }) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.TopAppBarContentColor
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDefaultListTopBar() {
    ComposeToDoTheme {
        Surface {
            DefaultListTopBar()
        }
    }
}