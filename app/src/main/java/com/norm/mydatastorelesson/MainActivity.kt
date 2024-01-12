package com.norm.mydatastorelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.norm.mydatastorelesson.ui.theme.MyDataStoreLessonTheme
import com.norm.mydatastorelesson.ui.theme.MyLightBlue
import com.norm.mydatastorelesson.ui.theme.MyLightGreen
import com.norm.mydatastorelesson.ui.theme.MyLightRed
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)
        setContent {
            MyDataStoreLessonTheme {
                val bgColorState = remember {
                    mutableStateOf(
                        MyLightRed.value
                    )
                }
                val textSizeState = remember {
                    mutableStateOf(
                        48
                    )
                }
                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSettings().collect { settings ->
                        bgColorState.value = settings.bgColor.toULong()
                        textSizeState.value = settings.textSize
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(bgColorState.value)
                ) {
                    MainScreen(dataStoreManager, textSizeState)
                }
            }
        }
    }
}

@Composable
fun MainScreen(dataStoreManager: DataStoreManager, textSizeState: MutableState<Int>) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.95f)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .wrapContentHeight(align = Alignment.CenterVertically)
        ) {
            Text(
                text = "My DataStore",
                color = Color.White,
                fontSize = textSizeState.value.sp
            )
        }
        Button(onClick = {
            scope.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        48,
                        MyLightRed.value.toLong()
                    )
                )
            }
        }) {
            Text(text = "Red")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        24,
                        MyLightGreen.value.toLong()
                    )
                )
            }
        }) {
            Text(text = "Green")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            scope.launch {
                dataStoreManager.saveSettings(
                    SettingsData(
                        12,
                        MyLightBlue.value.toLong()
                    )
                )
            }
        }) {
            Text(text = "Blue")
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}