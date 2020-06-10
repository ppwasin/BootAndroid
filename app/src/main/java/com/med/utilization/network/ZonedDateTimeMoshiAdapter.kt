package com.med.utilization.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime


class ZonedDateTimeMoshiAdapter {
	@FromJson
	fun eventFromJson(dateTimeString: String): ZonedDateTime {
		return DateExt.toApiZonedDateTime(dateTimeString)
	}

	@ToJson
	fun eventToJson(dateTime: ZonedDateTime): String {
		return DateExt.toApiDateFormatter(dateTime)
	}
}