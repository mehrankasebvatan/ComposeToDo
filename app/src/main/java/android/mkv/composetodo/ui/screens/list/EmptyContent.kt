package android.mkv.composetodo.ui.screens.list

import android.mkv.composetodo.R
import android.mkv.composetodo.ui.theme.Typography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter =
            painterResource(id = R.drawable.ic_no_tasks),
            contentDescription = "",
            Modifier.size(150.dp)
        )
        Text(
            text = "There is no tasks yet!",
            style = Typography.titleLarge,
            modifier = Modifier.padding(top = 20.dp),

            )
    }
}


