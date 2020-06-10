package com.med.coreui.recycleview

import android.os.Bundle
import timber.log.Timber

interface DiffItem {
	fun areItemTheSame(other: Any): Boolean
	fun areContentsTheSame(other: Any): Boolean {
		return this == other //it immutable, so we can use equals()
	}

	//By default, getChangePayload will return null. Which will make the searchResultAdapter do a full binding.
	//when you list => view not blink
	//see also: https://android.jlelse.eu/android-dtt-12-animate-recyclerview-with-diffutil-cac02b229911
	fun getChangePayload(other: Any): Any? {
		Timber.v("getChangePayload")
		val bundle = Bundle()
		bundle.putString("DEFAULT", "DEFAULT")
		return bundle //(see @MultiAdapter check that is not empty)
//        return "changepayload of ${javaClass.simpleName}"
	}
}