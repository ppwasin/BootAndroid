package com.med.utilization.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun FragmentContainerView.getNavCtrl(activity: AppCompatActivity): NavController? {
	val navHostFragment = activity.supportFragmentManager.findFragmentById(id) as? NavHostFragment
	return navHostFragment?.navController
}