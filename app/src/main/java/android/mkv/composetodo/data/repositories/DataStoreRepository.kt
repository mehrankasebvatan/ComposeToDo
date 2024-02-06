package android.mkv.composetodo.data.repositories

import android.content.Context
import android.mkv.composetodo.data.models.Priority
import android.mkv.composetodo.util.Constant.PREFERENCE_KEY
import android.mkv.composetodo.util.Constant.PREFERENCE_NAME
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


@ViewModelScoped
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private object PreferenceKey {
        val sortKey = stringPreferencesKey(PREFERENCE_KEY)
    }

    private val dataStore = context.dataStore

    suspend fun persistSortState(priority: Priority) {
        dataStore.edit { preference ->
            preference[PreferenceKey.sortKey] = priority.name
        }
    }

    val readState: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception

        }
        .map { preferences ->
            val sortState = preferences[PreferenceKey.sortKey] ?: Priority.NONE.name
            sortState
        }

}