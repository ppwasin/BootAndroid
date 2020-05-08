package com.med.utilization.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity
data class Entry(@PrimaryKey val id: Int, val dateTime: ZonedDateTime)