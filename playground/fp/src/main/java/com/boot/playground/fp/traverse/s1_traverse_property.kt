package com.boot.playground.fp.traverse

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.core.extensions.either.applicative.applicative
import arrow.core.extensions.list.traverse.traverse
import arrow.core.fix
import com.boot.playground.fp.runSection

fun propertyViaJVM(key: String): Either<String, String> {
    val result = System.getProperty(key)
    return if (result != null) {
        Right(result)
    } else {
        Left("No JVM property: $key")
    }
}

/*
    result1: [Right(test1.txt), Right(test2.txt), Right(test3.txt)]
    result2: [Right(test1.txt), Left(No JVM property: false), Right(test3.txt)]
*/
fun demoEither() {
    val data1 = listOf("foo", "bar", "zed")
    val result1 = data1.map(::propertyViaJVM)
    println("result1: $result1")

    val data2 = listOf("foo", "false", "zed")
    val result2 = data2.map(::propertyViaJVM)
    println("result2: $result2")
}


private fun demoTraverse(input: List<String>) {
    val failure = { error: String -> println(error) }
    val success = { results: List<String> ->
        println("Results are:")
        results.map { s -> "\t$s" }
            .forEach(::println)
    }

    val result = input.traverse(Either.applicative(), ::propertyViaJVM)
    result.fix()
        .fold(failure) { success(it.fix()) }
}

fun setup(){
    System.setProperty("foo", "test1.txt")
    System.setProperty("bar", "test2.txt")
    System.setProperty("zed", "test3.txt")
}

fun main() {
    setup()

    runSection("Demo Either"){
        demoEither()
    }

    runSection("Demo Demo Traverse: Success"){
        demoTraverse(listOf("foo", "bar", "zed"))
    }

    runSection("Demo Demo Traverse: Failure"){
        demoTraverse(listOf("foo", "false", "zed"))
    }

}