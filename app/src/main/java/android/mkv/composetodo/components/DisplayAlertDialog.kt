package android.mkv.composetodo.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.R
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (openDialog) {

        AlertDialog(
            onDismissRequest = { closeDialog() },
            confirmButton = {
                Button(onClick = { onYesClicked();closeDialog() }) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            },
            text = {
                Text(text = message,
                    style = MaterialTheme.typography.bodyLarge)
            },
            dismissButton = {
                TextButton(onClick = { closeDialog() }) {
                    Text(text = stringResource(R.string.cancel))
                }
            },

            )

    }

}

@Preview(showBackground = true, locale = "fa")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, locale = "fa")
@Composable
fun PreviewDisplayAlertDialog() {
    ComposeToDoTheme {
        Surface {
            DisplayAlertDialog(
                title = "Delete All",
                message = "Are you sure you want to delete all tasks?",
                openDialog = true,
                closeDialog = {},
                onYesClicked = {})


        }
    }
}