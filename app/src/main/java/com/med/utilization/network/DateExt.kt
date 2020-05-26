package com.med.utilization.network

import com.med.utilization.network.DateExt.Companion.LOCAL_TIME_ZONE
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun getBirthDateFormat(): SimpleDateFormat {
	val dateFormat = SimpleDateFormat("MM/dd/yyyy")
	dateFormat.timeZone = TimeZone.getTimeZone("UTC")
	return dateFormat
}

fun ZonedDateTime.toLocalStringFormat(pattern: String): String {
	return format(DateTimeFormatter.ofPattern(pattern))
}

fun ZonedDateTime.isOnSameDay(date: ZonedDateTime): Boolean {
	return toLocalDate() == date.toLocalDate()
}

fun ZonedDateTime.isLocalToday(): Boolean {
	return toLocalDate() == LocalDate.now(ZoneId.systemDefault())
}

fun Date.isLocalToday(): Boolean {
	val datetime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.time), LOCAL_TIME_ZONE)
	return datetime.isLocalToday()
}

class DateExt {
	companion object {
		val ZERO_TIME_ZONE = ZoneOffset.UTC
		val LOCAL_TIME_ZONE = ZoneId.systemDefault()
		const val API_FORMAT_STR = "yyyy-MM-dd HH:mm:ss"
		const val FULL_DAY_MONTH_YEAR = "dd MMMM yyy"
		const val API_QUERY_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss"
		val LOCAL_CALENDAR = Calendar.getInstance()

		fun toApiZonedDateTime(apiDateStr: String): ZonedDateTime {
			val dateTime =
				LocalDateTime.parse(apiDateStr, DateTimeFormatter.ofPattern(API_FORMAT_STR))
			return dateTime.atZone(ZERO_TIME_ZONE)
		}

		fun toApiMill(apiDateStr: String): Long {
			return toApiMill(toApiZonedDateTime(apiDateStr))
		}

		fun toApiMill(zonedDateTime: ZonedDateTime): Long {
			return zonedDateTime.toInstant().toEpochMilli()
		}

		fun createLocalDateTimeMonthStartFromOne(year: Int, month: Int, day: Int): ZonedDateTime {
			val localDateTime = LocalDateTime.of(year, month, day, 23, 59, 59)
			Timber.v("$year, $month, $day, result: $localDateTime")
			return localDateTime.atZone(LOCAL_TIME_ZONE)
		}

		fun getLocalToday(): ZonedDateTime {
			return ZonedDateTime.now()
		}

		fun toApiDateFormatter(zonedDateTime: ZonedDateTime): String {
			Timber.v("ZonedDateTime: $this")
			val test = toApiZone(zonedDateTime).toLocalStringFormat(API_QUERY_DATE_FORMAT)
			Timber.v("input api: $test")
			return test
		}

		fun toApiZone(zonedDateTime: ZonedDateTime): ZonedDateTime {
			return zonedDateTime.withZoneSameInstant(ZERO_TIME_ZONE)
		}
	}
}

