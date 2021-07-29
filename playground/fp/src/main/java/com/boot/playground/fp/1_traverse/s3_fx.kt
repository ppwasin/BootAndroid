package com.boot.playground.fp.`1_traverse`

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.core.computations.either
import arrow.core.extensions.either.applicative.applicative
import arrow.core.extensions.list.traverse.traverse
import arrow.core.fix
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

fun readAllLines(path: String): Either<String, List<String>> = try {
    Right(
        Files.lines(Paths.get(rootPath, path))
            .toList()
    )
} catch (ex: NoSuchFileException) {
    Left("No file called $path")
}

private suspend fun demoTraverse(data: List<String>) {
    val findViaJVM = { input: List<String> ->
        input.traverse(Either.applicative(), ::propertyViaJVM)
    }

    val findViaFile = { input: List<String> ->
        val file = "part3.properties"
        input.traverse(Either.applicative()) {
            propertyViaFile(file, it)
        }
    }

    val readEverything =
        { paths: List<String> -> paths.traverse(Either.applicative(), ::readAllLines).fix() }

    val result = either<String, List<String>> {
        val a = !findViaJVM(data)
        val b = !findViaFile(a.fix())
        val c = !readEverything(b.fix())
        c.fix().flatten()
    }

    result.fold(
        { error -> println("The process failed!\n\t$error") },
        { lines ->
            println("The process was successful:")
            lines.map { "\t$it" }
                .forEach(::println)
        }
    )
}

suspend fun main() {
    System.setProperty("cagney", "cagney.lacy")
    System.setProperty("starsky", "starsky.hutch")
    System.setProperty("hart", "hart.hart")

    demoTraverse(listOf("cagney", "starsky", "hart"))
}