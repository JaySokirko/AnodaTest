package com.jay.anodatest.util

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan

fun String.highlightLabeledText(color: Int, vararg labels: String): SpannableStringBuilder {
    val builder = SpannableStringBuilder()
    val array: List<String> = this.split(" ")

    array.forEach { text: String ->

        if (text.startsWith(labels)) {
            val spannableString: SpannableString = SpannableString(text).apply {
                setSpan(ForegroundColorSpan(color), 0, text.length, 0)
            }
            builder.append(spannableString)
            builder.append(" ")
        } else {
            builder.append(text)
            builder.append(" ")
        }
        builder.trim()
    }
    return builder
}

fun String.startsWith(symbols: Array<out String>): Boolean {
    for (symbol: String in symbols) {
        if (this.startsWith(symbol)) {
            return true
        }
    }
    return false
}

fun String.splitTextByChar(char: String): Pair<Int, String> {
    val array: List<String> = this.trim().split(" ")
    val builder = StringBuilder()
    var splitPositions = 0

    array.forEachIndexed { index, string ->
        val lastIterate: Boolean = index == array.size -1

        builder.append(string)

        if (lastIterate) return@forEachIndexed

        builder.append(char)
        builder.append(" ")

        splitPositions++
    }

    return Pair(splitPositions, builder.toString())
}

fun String.removeLastChar(): String {
    return if (this.isEmpty()) ""
    else this.substring(0, this.length - 1)
}