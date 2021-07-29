package com.boot.playground.fp.`3_hk`

import arrow.Kind2
import arrow.core.*

object ArrowHkSample {
    interface Competition<T> {
        fun <U, V> judge(input: Kind2<T, U, V>): String
    }

    object EitherCompetition : Competition<ForEither> {
        override fun <U, V> judge(input: Kind2<ForEither, U, V>): String {
            return if (input.fix().isRight()) "winner" else "loser"
        }
    }

    object ValidatedCompetition : Competition<ForValidated> {
        override fun <U, V> judge(input: Kind2<ForValidated, U, V>): String {
            return if (input.fix().isValid) "winner" else "loser"
        }
    }
}

fun main() {
    val tst1 = Right(123)
    val tst2 = Left(456)
    val tst3 = Valid(123)
    val tst4 = Invalid(456)

    println(ArrowHkSample.EitherCompetition.judge(tst1))
    println(ArrowHkSample.EitherCompetition.judge(tst2))
    println(ArrowHkSample.ValidatedCompetition.judge(tst3))
    println(ArrowHkSample.ValidatedCompetition.judge(tst4))
}