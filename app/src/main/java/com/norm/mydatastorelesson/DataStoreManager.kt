package com.norm.mydatastorelesson

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.norm.mydatastorelesson.ui.theme.MyLightRed
import kotlinx.coroutines.flow.map

//setting a table to store data
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {
    suspend fun saveSettings(settingData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[intPreferencesKey("test_size")] = settingData.textSize
            pref[longPreferencesKey("bg_color")] = settingData.bgColor.toLong()
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData(
            pref[intPreferencesKey("test_size")] ?: 48,
            pref[longPreferencesKey("bg_color")]?.toULong() ?: MyLightRed.value
        )
    }
}