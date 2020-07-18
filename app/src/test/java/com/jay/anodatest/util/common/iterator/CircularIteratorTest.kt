package com.jay.anodatest.util.common.iterator

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CircularIteratorTest {

    private val collection = listOf(1, 2, 3)
    private lateinit var circleIterator: CircularIterator<Int>

    @Before
    fun beforeEach() {
        circleIterator = CircularIterator()
        circleIterator.setCollection(collection)
    }

    @Test
    fun setCollection_correctAddsElements() {
        val expectSize: Int = collection.size
        val actualSize: Int = circleIterator.getSize()

        assertEquals(expectSize, actualSize)
    }

    @Test
    fun setCollection_notDuplicatesCollection() {
        circleIterator.setCollection(collection)
        circleIterator.setCollection(collection)

        val expectSize: Int = collection.size
        val actualSize: Int = circleIterator.getSize()

        assertEquals(expectSize, actualSize)
    }

    @Test
    fun isNotLatest() {
        repeat(circleIterator.getSize()) { position ->
            val lastPosition: Boolean = (collection.size - 1) == position

            if (lastPosition) {
                circleIterator.getNext()
                assertEquals(false, circleIterator.isNotLatest())
            } else {
                circleIterator.getNext()
                assertEquals(true, circleIterator.isNotLatest())
            }
        }
    }

    @Test
    fun isNotFirst() {
        repeat(circleIterator.getSize()) { position ->
            val firstPosition: Boolean = position == 0

            if (firstPosition) {
                circleIterator.getPrevious()
                assertEquals(false, circleIterator.isNotFirst())
            } else {
                circleIterator.getPrevious()
                assertEquals(true, circleIterator.isNotFirst())
            }
        }
    }

    @Test
    fun getNext() {
        assertEquals(1, circleIterator.getNext())
        assertEquals(2, circleIterator.getNext())
        assertEquals(3, circleIterator.getNext())
    }

    @Test
    fun getNext_collectionIteration_shouldGoes_inCircle() {
        assertEquals(1, circleIterator.getNext())
        assertEquals(2, circleIterator.getNext())
        assertEquals(3, circleIterator.getNext())
        assertEquals(1, circleIterator.getNext())
        assertEquals(2, circleIterator.getNext())
        assertEquals(3, circleIterator.getNext())
        assertEquals(1, circleIterator.getNext())
    }

    @Test
    fun getPrevious() {
        assertEquals(1, circleIterator.getPrevious())
        assertEquals(3, circleIterator.getPrevious())
        assertEquals(2, circleIterator.getPrevious())
    }

    @Test
    fun getPrevious_collectionIteration_shouldGoes_inCircle() {
        assertEquals(1, circleIterator.getPrevious())
        assertEquals(3, circleIterator.getPrevious())
        assertEquals(2, circleIterator.getPrevious())
        assertEquals(1, circleIterator.getPrevious())
        assertEquals(3, circleIterator.getPrevious())
        assertEquals(2, circleIterator.getPrevious())
        assertEquals(1, circleIterator.getPrevious())
    }

    @Test
    fun resetToStart() {
        assertEquals(1, circleIterator.getNext())
        assertEquals(2, circleIterator.getNext())
        circleIterator.resetToStart()
        assertEquals(1, circleIterator.getNext())
    }

    @Test
    fun resetToEnd() {
        assertEquals(1, circleIterator.getPrevious())
        assertEquals(3, circleIterator.getPrevious())
        circleIterator.resetToEnd()
        assertEquals(3, circleIterator.getPrevious())
    }

    @Test
    fun getSize() {
        assertEquals(collection.size, circleIterator.getSize())
    }
}