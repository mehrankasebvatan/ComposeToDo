package android.mkv.composetodo.ui.screens.list

import android.mkv.composetodo.R
import android.mkv.composetodo.components.DisplayAlertDialog
import android.mkv.composetodo.components.PriorityItem
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.theme.TopAppBarContentColor
import android.mkv.composetodo.ui.theme.Typography
import android.mkv.composetodo.ui.viewmodels.SharedViewModel
import android.mkv.composetodo.util.Action
import android.mkv.composetodo.util.SearchTopBarState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListTopBar(
    sharedViewModel: SharedViewModel,
    searchTopBarState: SearchTopBarState,
    searchTextState: String
) {
    when (searchTopBarState) {
        SearchTopBarState.CLOSED -> {
            DefaultListTopBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchTopBarState.OPENED
                },
                onSortClicked = { priority ->
                    sharedViewModel.persistSortingState(priority)
                },
                onDeleteClicked = {

                    sharedViewModel.action.value = Action.DELETE_ALL
                }
            )

        }

        else -> {
            SearchTopBar(
                text = searchTextState,
                onTextChanged = {
                    sharedViewModel.searchTextState.value = it
                },
                onCloseClick = {
                    sharedViewModel.searchAppBarState.value =
                        SearchTopBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {
                    sharedViewModel.searchDatabase(it)
                })

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search),
                            color = Color.LightGray
                        )
                    },
                    value = text,
                    onValueChange = {
                        onTextChanged(it)
                    },
                    leadingIcon = {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "",
                                tint = Color.LightGray

                            )
                        }

                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            if (text.isNotEmpty()) onTextChanged("")
                            else onCloseClick()

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",
                                tint = Color.White,
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = Color.Red,
                        focusedTextColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClicked(text)

                        }
                    )

                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListTopBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.TopAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            DefaultListTopBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        }
    )
}

@Composable
fun DefaultListTopBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_all_question_),
        message = stringResource(R.string.are_you_sure_you_want_to_delete_all_tasks),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = {
            onDeleteClicked()
        })


    SearchAction {
        onSearchClicked()
    }
    SortAction {
        onSortClicked(it)
    }
    DeleteAllAction {
        openDialog = true
    }
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
    onSortClick: (Priority) -> Unit
) {
    var expended by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = {
        expended = true
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_sort),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.TopAppBarContentColor
        )




        DropdownMenu(expanded = expended,
            onDismissRequest = {
                expended = false
            }) {

            Priority.entries.toTypedArray().slice(setOf(0, 2, 3)).forEach { priority ->
                DropdownMenuItem(
                    text = {
                        PriorityItem(priority = priority)
                    },
                    onClick = {
                        expended = false
                        onSortClick(priority)
                    })
            }
        }
    }
}


@Composable
fun DeleteAllAction(
    onDeleteAction: () -> Unit
) {

    var expended by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = {
        expended = true
    }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.TopAppBarContentColor
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = {
                expended = false
            }) {
            DropdownMenuItem(text = {
                Text(
                    text = stringResource(R.string.delete_all_question),
                    modifier = Modifier.padding(start = 3.dp),
                    style = Typography.titleMedium
                )
            },
                onClick = {
                    expended = false
                    onDeleteAction()
                })

//            DropdownMenuItem(text = {
//                Column {
//
//                    Text(text = stringResource(R.string.language))
//
//                }
//            }, onClick = {
//                expended = false
//            })
        }
    }
}


@Preview(locale = "fa", showBackground = true)
@Composable
fun PreviewDefault() {
    ComposeToDoTheme {
        Surface {
            DefaultListTopBar(
                onSearchClicked = { },
                onSortClicked = {},
                onDeleteClicked = {})
        }
    }

}

@Preview(locale = "fa")
@Composable
fun PreviewSearch() {
    ComposeToDoTheme {
        Surface {
            SearchTopBar(
                text = "",
                onTextChanged = {},
                onCloseClick = {},
                onSearchClicked = {})
        }
    }
}