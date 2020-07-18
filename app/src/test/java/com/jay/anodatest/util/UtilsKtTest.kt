package com.jay.anodatest.util

import junit.framework.Assert.assertEquals
import org.junit.Test

class UtilsKtTest {

    @Test
    fun startsWith() {
        val text = "@text"
        val expected = true
        val actual: Boolean = text.startsWith("@")
        assertEquals(expected, actual)
    }

    @Test
    fun removeLastChar() {
        val text = "text"
        val expected = "tex"
        val actual: String = text.removeLastChar()
        assertEquals(expected, actual)
    }
}