package android.mkv.composetodo.data.models

import android.mkv.composetodo.R
import android.mkv.composetodo.ui.theme.HighPriorityColor
import android.mkv.composetodo.ui.theme.LowPriorityColor
import android.mkv.composetodo.ui.theme.MediumPriorityColor
import android.mkv.composetodo.ui.theme.NonePriorityColor
import androidx.compose.ui.graphics.Color


enum class Priority(val color: Color, val title: Int) {
    HIGH(HighPriorityColor, R.string.high),
    MEDIUM(MediumPriorityColor, R.string.medium),
    LOW(LowPriorityColor, R.string.low),
    NONE(NonePriorityColor, R.string.none)
}
