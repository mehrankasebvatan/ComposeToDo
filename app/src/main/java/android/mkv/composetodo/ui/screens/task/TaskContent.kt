package android.mkv.composetodo.ui.screens.task

import android.mkv.composetodo.R
import android.mkv.composetodo.components.PriorityDropDown
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import android.mkv.composetodo.ui.theme.LARGE_PADDING
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPriorityChange: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LARGE_PADDING)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = {
                onTitleChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.title))
            },
            singleLine = true
        )
        PriorityDropDown(priority = priority, onPrioritySelected = {
            onPriorityChange(it)

        })
        OutlinedTextField(
            value = description,
            onValueChange = {
                onDescriptionChange(it)
            },
            modifier = Modifier.fillMaxSize(),
            label = {
                Text(text = stringResource(R.string.description))
            },
        )
    }
}

@Preview(showSystemUi = true, locale = "fa")
@Composable
fun PreviewTaskContent() {
    ComposeToDoTheme {
        Surface {
            TaskContent(
                title = "Title",
                onTitleChange = {},
                description = "Description",
                onDescriptionChange = {},
                priority = Priority.LOW,
                onPriorityChange = {}
            )
        }
    }
}