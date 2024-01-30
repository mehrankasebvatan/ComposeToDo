package android.mkv.composetodo.data.models

import android.mkv.composetodo.ui.theme.HighPriorityColor
import android.mkv.composetodo.ui.theme.LowPriorityColor
import android.mkv.composetodo.ui.theme.MediumPriorityColor
import android.mkv.composetodo.ui.theme.NonePriorityColor
import androidx.compose.ui.graphics.Color


enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}