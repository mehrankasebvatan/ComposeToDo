package android.mkv.composetodo.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.ui.theme.ComposeToDoTheme
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f, label = ""
    )


    Column(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 7.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable { expanded = true }
                .alpha(0.7f)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(4.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Canvas(
                modifier = Modifier
                    .size(16.dp)
                    .weight(1f)
            ) {
                drawCircle(color = priority.color)
            }
            Text(text = priority.name, modifier = Modifier.weight(8f))
            IconButton(
                onClick = {
                    expanded = true
                },
                modifier = Modifier
                    .rotate(angle)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    modifier = Modifier.weight(
                        1.5f
                    )
                )
            }
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(fraction = 0.94f)
        ) {
            DropdownMenuItem(text = {
                PriorityItem(priority = Priority.LOW)
            }, onClick = {
                expanded = false
                onPrioritySelected(Priority.LOW)
            })
            DropdownMenuItem(text = {
                PriorityItem(priority = Priority.MEDIUM)
            }, onClick = {
                expanded = false
                onPrioritySelected(Priority.MEDIUM)
            })
            DropdownMenuItem(text = {
                PriorityItem(priority = Priority.HIGH)
            }, onClick = {
                expanded = false
                onPrioritySelected(Priority.HIGH)
            })
        }
    }

}

@Preview(showSystemUi = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPriorityDropDown() {
    ComposeToDoTheme {
        Surface {
            PriorityDropDown(priority = Priority.HIGH, onPrioritySelected = {})
        }
    }
}