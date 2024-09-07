package com.uenp.codegenerator.utils

fun normalize(value: String): String {
    return value.lowercase().replace(" ", "-")
}