package com.boot.playground.fp.`4_kleisli`

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right

object KleisliSample {
    fun propertyViaJVM(key: String): Either<String, String> {
        val result = System.getProperty(key)
        return if (result != null) {
            Right(result)
        } else {
            Left("No JVM property: $key")
        }
    }
    fun addProperties(vararg items: String) {
        items.reduce { last, current ->
            System.setProperty(last, current)
            current
        }
    }

    fun printProperties() {
        System.getProperties()
            .entries
            .map { (key, value) ->
                val paddedKey = key.toString().padStart(20)
                "$paddedKey | $value"
            }
            .forEach(::println)
    }

}

fun addAtreidesLineage() = KleisliSample.addProperties(
    "Vladimir",
    "Jessica",
    "Paul",
    "Ghanima",
    "Moneo",
    "Siona"
)


fun main() {
    addAtreidesLineage()
    KleisliSample.printProperties()
}