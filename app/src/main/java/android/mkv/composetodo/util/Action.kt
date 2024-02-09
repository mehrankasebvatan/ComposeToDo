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
    return if (this.isNullOrEmpty()) Action.NO_ACTION
    else Action.valueOf(this)

}