package com.med.coreui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner

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

inline fun <reified T : ViewModel> ComponentActivity.savedStateViewModelWithProvider(
	defaultArgs: Bundle? = null,
	crossinline provider: (SavedStateHandle) -> T
) = viewModels<T> {
	object : AbstractSavedStateViewModelFactory(this, defaultArgs) {
		override fun <T : ViewModel> create(
			key: String,
			modelClass: Class<T>,
			handle: SavedStateHandle
		): T {
			@Suppress("UNCHECKED_CAST")
			return provider(handle) as T
		}
	}
}

inline fun <reified T : ViewModel> Fragment.savedStateViewModelWithProvider(
	noinline ownerProducer: () -> ViewModelStoreOwner = { this },
	noinline savedStateRegistryOwnerProducer: () -> SavedStateRegistryOwner = { this },
	defaultArgs: Bundle? = null,
	crossinline provider: (SavedStateHandle) -> T
) = viewModels<T>(ownerProducer) {
	object : AbstractSavedStateViewModelFactory(savedStateRegistryOwnerProducer(), defaultArgs) {
		override fun <T : ViewModel> create(
			key: String,
			modelClass: Class<T>,
			handle: SavedStateHandle
		): T {
			@Suppress("UNCHECKED_CAST")
			return provider(handle) as T
		}
	}
}