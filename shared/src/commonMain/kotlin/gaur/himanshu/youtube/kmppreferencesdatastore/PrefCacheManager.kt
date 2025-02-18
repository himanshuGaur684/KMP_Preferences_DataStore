package gaur.himanshu.youtube.kmppreferencesdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PrefCacheManager(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun save(key: String, value: String) {
        return withContext(Dispatchers.IO) {
            try {
                dataStore.edit {
                    it[stringPreferencesKey(key)] = value
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    suspend fun getValue(key: String): String {
        return dataStore
            .data
            .map {
                it[stringPreferencesKey(key)] ?: "Error"
            }.first()
    }


}