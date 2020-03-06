package com.med.coreui

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified VM : ViewModel> Fragment.viewModelWithFactory(
	noinline ownerProducer: () -> ViewModelStoreOwner = { this },
	crossinline createViewModel: () -> VM
) = viewModels<VM>(ownerProducer) {
	object : ViewModelProvider.Factory {
		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return createViewModel() as T
		}
	}
}

inline fun <reified VM : ViewModel> ComponentActivity.viewModelWithFactory(
	crossinline createViewModel: () -> VM
) = viewModels<VM> {
	object : ViewModelProvider.Factory {
		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return createViewModel() as T
		}
	}
}