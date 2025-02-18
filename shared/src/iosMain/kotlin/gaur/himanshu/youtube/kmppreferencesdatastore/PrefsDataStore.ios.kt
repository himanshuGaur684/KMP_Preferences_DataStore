package gaur.himanshu.youtube.kmppreferencesdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSFileTypeDirectory
import platform.Foundation.NSUserDomainMask
import platform.posix.err

@OptIn(ExperimentalForeignApi::class)
actual fun createDataStore(context:Any?): DataStore<Preferences> {
   return createDataStore(
        producePath = {
            val documentDirectory = NSFileManager.defaultManager
                .URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null
                )
            requireNotNull(documentDirectory).path?.plus("/").plus(datastoreFileName)
        }
    )
}

object IosDataStore {
    fun getCreateDataStore() : DataStore<Preferences> = createDataStore(null)
}