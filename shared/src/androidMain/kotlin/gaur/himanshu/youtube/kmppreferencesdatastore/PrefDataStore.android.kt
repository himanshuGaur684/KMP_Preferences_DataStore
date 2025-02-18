package gaur.himanshu.youtube.kmppreferencesdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(context: Any?): DataStore<Preferences> {

  return  createDataStorePref {
        (context as Context)
            .filesDir
            .resolve(dataStoreFileName)
            .absolutePath
    }

}