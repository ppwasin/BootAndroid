package com.med.utilization.db.converter

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

class DateTimeConverter {
	@TypeConverter
	fun fromTimestamp(value: Long): ZonedDateTime {
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC)
	}

	@TypeConverter
	fun dateToTimestamp(date: ZonedDateTime): Long {
		return date.toInstant().toEpochMilli()
	}
}