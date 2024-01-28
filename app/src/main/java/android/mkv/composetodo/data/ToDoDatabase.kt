package android.mkv.composetodo.data

import android.mkv.composetodo.data.models.ToDoTask
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    
    abstract fun toDoDao(): ToDoDao
}