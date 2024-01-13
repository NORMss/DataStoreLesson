package com.norm.mydatastorelesson

import android.content.Context
import androidx.datastore.dataStore

private val Context.protoDataStore by dataStore("settings.json", SettingsSerializer)

class ProtoDataStoreManager(val context: Context) {
    suspend fun saveColor(color: ULong) {
        context.protoDataStore.updateData { data ->
            data.copy(bgColor = color)
        }
    }

    suspend fun saveTextSize(size: Int) {
        context.protoDataStore.updateData { data ->
            data.copy(textSize = size)
        }
    }

    suspend fun saveSettings(settingsData: SettingsData) {
        context.protoDataStore.updateData { data ->
            settingsData
        }
    }

    fun getSettings() = context.protoDataStore.data
}