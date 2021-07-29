package com.boot.playground.fp

fun runSection(title: String, run: () -> Unit) {
    println("==========$title===========")
    run()
    println()
}