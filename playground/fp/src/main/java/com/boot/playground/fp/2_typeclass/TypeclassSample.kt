package com.boot.playground.fp.`2_typeclass`

import java.math.BigDecimal

object TypeClass {
    interface Addable<T> {
        fun T.plus(t: T): T
        fun zero(): T

        companion object {
            fun <T> instance(plus: (T, T) -> T, zero: () -> T): Addable<T> {
                return object : Addable<T> {
                    override fun T.plus(t: T) = plus(this, t)
                    override fun zero(): T = zero()
                }
            }
        }
    }

    fun <T> Iterable<T>.sum(addable: Addable<T>): T {
        var sum: T = addable.zero()
        for (element in this) {
            addable.run {
                sum = sum.plus(element)
            }
        }
        return sum
    }

    fun bigDecimalAddable(): Addable<BigDecimal> =
        Addable.instance(
            plus = { a, b -> a + b },
            zero = { BigDecimal(0) }
        )
}

object DataClassVersion {
    data class Addable<T>(
        val plus: (T, T) -> T,
        val zero: () -> T
    )
    fun <T> Iterable<T>.sum(addable: Addable<T>): T {
        var sum: T = addable.zero()
        for (element in this) {
            sum = addable.plus(sum, element)
        }
        return sum
    }

    fun bigDecimalAddable(): Addable<BigDecimal> =
        Addable(
            plus = { a, b -> a + b },
            zero = { BigDecimal(0) }
        )
}


object ExtensionVersion {
    fun Iterable<BigDecimal>.sum(): BigDecimal {
        var sum = BigDecimal(0)
        for (element in this) {
            sum += element
        }
        return sum
    }
}


fun main() {
    ExtensionVersion.run {
        listOf(BigDecimal(1), BigDecimal(2), BigDecimal(3))
            .sum()
            .also { println("ExtensionVersion sum: $it") }
    }

    // TypeClass and DataClassVersion make more decouple.
    // The Addable interface and data class is the contact that must be satisfied
    TypeClass.run {
        listOf(BigDecimal(1), BigDecimal(2), BigDecimal(3))
            .sum(bigDecimalAddable())
            .also { println("TypeClass sum: $it") }
    }

    // DataClassVersion is more less code than TypeClass
    DataClassVersion.run {
        listOf(BigDecimal(1), BigDecimal(2), BigDecimal(3))
            .sum(bigDecimalAddable())
            .also { println("DataClass sum: $it") }
    }

}