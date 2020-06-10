package com.med.coreui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import timber.log.Timber

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, render: (T) -> Unit) {
	observe(lifecycleOwner, Observer {
		if (it != null) render(it)
		else Timber.w("observeNotNull is null")
	})
}

