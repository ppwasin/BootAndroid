package com.boot.projectMgr.ex.textfield.phone

fun String.substringSafe(start: Int, end: Int = length): String {
	return when {
		start > length -> ""
		end > length -> substring(start)
		else -> substring(start, end)
	}
	
}