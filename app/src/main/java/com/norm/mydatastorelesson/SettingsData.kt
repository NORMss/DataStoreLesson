package com.norm.mydatastorelesson

import com.norm.mydatastorelesson.ui.theme.MyLightRed
import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val textSize: Int = 48,
    val bgColor: ULong = MyLightRed.value
)
