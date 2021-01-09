package com.boot.playground.fp.traverse

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import arrow.core.extensions.either.applicative.applicative
import arrow.core.extensions.list.traverse.traverse
import arrow.core.fix
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

fun propertyViaFile(path: String, name: String): Either<String, String> {
    val propertyValue = { str: String -> str.substring(str.indexOf("=") + 1) }
    return try {
        val filePath = Paths.get(rootPath, path)
        val lines = Files.lines(filePath)
        lines
            .filter { it.startsWith("$name=") }
            .findFirst()
            .map(propertyValue)
            .map { Right(it) as Either<String, String> }
            .orElse(Left("No property called $name"))
    } catch (ex: Exception) {
        Left("No file called $path locate at $rootPath")
    }
}

private fun demoTraverse(fileName: String, input: List<String>) {
    val failure = { error: String -> println("Either.failure: $error") }

    val success = { results: List<String> ->
        println("Either.success:")
        results.map { s -> "\t$s" }
            .forEach(::println)
    }

    val readInFile = { name: String -> propertyViaFile(fileName, name) }

    val result = input.traverse(Either.applicative(), readInFile)

    result.fix()
        .fold(failure) { success(it.fix()) }
}

fun main() {
    val correctName = "part2.properties"
    val incorrectName = "false.properties"

    demoTraverse(correctName, listOf("foo", "bar", "zed"))
    demoTraverse(correctName, listOf("false", "bar", "zed"))
    demoTraverse(correctName, listOf("foo", "false", "zed"))
    demoTraverse(correctName, listOf("foo", "bar", "false"))
    demoTraverse(incorrectName, listOf("foo", "bar", "false"))
}