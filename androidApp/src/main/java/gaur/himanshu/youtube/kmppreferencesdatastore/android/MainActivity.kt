package gaur.himanshu.youtube.kmppreferencesdatastore.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import gaur.himanshu.youtube.kmppreferencesdatastore.StoreManager
import gaur.himanshu.youtube.kmppreferencesdatastore.createDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val scope = rememberCoroutineScope()
                    var key by rememberSaveable { mutableStateOf("") }
                    var value by rememberSaveable { mutableStateOf("") }
                    var savedValue by rememberSaveable { mutableStateOf("") }
                    val storeManager = remember {
                        StoreManager(
                            createDataStore(context)
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(savedValue)
                        Spacer(Modifier.height(12.dp))
                        TextField(value = key, onValueChange = {
                            key = it
                        }, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(12.dp))
                        TextField(value = value, onValueChange = {
                            value = it
                        }, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(12.dp))
                        Button(onClick = {
                            scope.launch(Dispatchers.IO) {
                                storeManager.save(
                                    key, value
                                )
                            }
                        }) {
                            Text("Save")
                        }
                        Spacer(Modifier.height(12.dp))
                        Button(onClick = {
                            scope.launch(Dispatchers.IO) {
                                savedValue = storeManager.getValue(key)
                            }
                        }) { Text("Get") }
                    }

                }
            }
        }
    }
}
