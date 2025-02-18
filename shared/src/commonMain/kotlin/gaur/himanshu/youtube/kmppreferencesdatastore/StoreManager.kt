package gaur.himanshu.youtube.kmppreferencesdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class StoreManager(
    private val store:DataStore<Preferences>
) {

    suspend fun save(key:String,value:String){
        withContext(Dispatchers.IO){
            store
                .edit {
                    it[stringPreferencesKey(key)] = value
                }
        }

    }

    suspend fun getValue(key:String):String{
       return withContext(Dispatchers.IO){
            store
                .data
                .map {
                    it[stringPreferencesKey(key)] ?: "error"
                }
        }.first()
    }

}