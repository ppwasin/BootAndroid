package com.med.utilization.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode

fun AppCompatDelegate.shouldEnableDarkMode(darkModeConfig: DarkModeConfig){
  when(darkModeConfig){
	DarkModeConfig.YES -> setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
	DarkModeConfig.NO -> setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
	DarkModeConfig.FOLLOW_SYSTEM -> setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
  }
}