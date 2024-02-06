package android.mkv.composetodo.util

import android.mkv.composetodo.R

enum class Action(val title: Int) {
    ADD(R.string.add),
    UPDATE(R.string.update),
    DELETE(R.string.delete),
    DELETE_ALL(R.string.delete_all),
    UNDO(R.string.undo_success),
    NO_ACTION(R.string.no_action)
}

fun String?.toAction(): Action {
    return when {
        this == "ADD" -> Action.ADD
        this == "UPDATE" -> Action.UPDATE
        this == "DELETE" -> Action.DELETE
        this == "DELETE_ALL" -> Action.DELETE_ALL
        this == "UNDO" -> Action.UNDO
        else -> Action.NO_ACTION
    }
}