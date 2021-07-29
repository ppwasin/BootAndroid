package com.boot.paging3.data.rick_morty

import android.graphics.pdf.PdfDocument
import com.squareup.moshi.Json

data class PagedResponse<T>(
    @Json(name = "info") val pageInfo: PdfDocument.PageInfo,
    val results: List<Character> = listOf()
)