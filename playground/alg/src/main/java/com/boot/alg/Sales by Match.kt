package com.boot.alg

typealias Number = Int
typealias SameCount = Int

fun sockMerchant(n: Int, ar: Array<Int>): Int {
    val sameNumbers = mutableMapOf<Number, SameCount>()
    var numberOfPairs = 0
    ar.forEach {
        sameNumbers[it] = (sameNumbers[it] ?: 0) + 1

        val newValue = sameNumbers[it] ?: 0
        if (newValue != 0 && newValue % 2 == 0)
            numberOfPairs += 1
    }
    return numberOfPairs
}
