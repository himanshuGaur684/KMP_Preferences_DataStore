package gaur.himanshu.youtube.kmppreferencesdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun createDataStore(context: Any?): DataStore<Preferences> {

    return createDataStorePref {
        val documentDirectory = NSFileManager
            .defaultManager
            .URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                error = null,
                create = false
            )
        requireNotNull(documentDirectory).path.plus("/").plus(dataStoreFileName)
    }

}

object PreDataStoreForIos {
    fun getDataStore() = createDataStore(null)
}
