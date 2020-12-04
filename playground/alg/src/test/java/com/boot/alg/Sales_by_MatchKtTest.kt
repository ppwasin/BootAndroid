package com.boot.alg

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Sales_by_MatchKtTest {
    @Test
    fun test() {
        assertEquals(3, sockMerchant(10, arrayOf(10, 20, 20, 10, 10, 30, 50, 10, 20)))
    }
}