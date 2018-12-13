package com.avolosko.spacex.core

import android.content.Context
import android.content.SharedPreferences

class UserSettings(context: Context) {
    private val PREFS_FILENAME = "com.avolosko.spacex.prefs"
    private val FIRST_LAUNCH = "first_launch"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean(FIRST_LAUNCH, true)
        set(value) = prefs.edit().putBoolean(FIRST_LAUNCH, value).apply()
}