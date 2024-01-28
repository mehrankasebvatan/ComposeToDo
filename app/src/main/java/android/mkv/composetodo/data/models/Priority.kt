package android.mkv.composetodo.data.models

import android.mkv.composetodo.ui.theme.highPriorityColor
import android.mkv.composetodo.ui.theme.lowPriorityColor
import android.mkv.composetodo.ui.theme.mediumPriorityColor
import android.mkv.composetodo.ui.theme.nonePriorityColor
import androidx.compose.ui.graphics.Color


enum class Priority(val color: Color) {
    HIGH(highPriorityColor),
    MEDIUM(mediumPriorityColor),
    LOW(lowPriorityColor),
    NONE(nonePriorityColor)
}