package com.vagnnermartins.baseproject.domain

import java.util.*

fun randomString(): String {
    return UUID.randomUUID().toString()
}

fun randomInt(): Int {
    return Random().nextInt()
}

fun randomBoolean(): Boolean {
    return Random().nextInt() > 0.5
}

fun randomLong(): Long {
    return Random().nextLong()
}