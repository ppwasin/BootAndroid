package com.med.dynamicfeature.installer

import java.util.*

inline fun <reified T> SplitInstallerSyntax.loadFeature(feature: Feature): T? {
	return if (isFeatureInstalled(feature)) {
		loadClass<T>()
	} else {
		null
	}
}

inline fun <reified T> loadClass(): T? {
	val serviceIterator = ServiceLoader.load(
		T::class.java,
		T::class.java.classLoader
	).iterator()
	println("${serviceIterator.hasNext()}")
	return if (serviceIterator.hasNext()) {
		serviceIterator.next()
	} else {
		null
	}
}