package com.boot.paging3.data.rick_morty

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameUrl(
    val name: String,
    val url: String
): Parcelable