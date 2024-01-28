package android.mkv.composetodo.data.models

import android.mkv.composetodo.util.Constant.DATABASE_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val priority: Priority = Priority.NONE
)