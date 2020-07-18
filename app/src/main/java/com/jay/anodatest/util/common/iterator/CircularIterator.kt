package com.jay.anodatest.util.common.iterator

/**
 * This iterator allows forward and backward iteration of the elements in the collection.
 * @see CircularForwardIterator
 * @see CircularBackwardIterator
 */
class CircularIterator<T> : CircularBackwardIterator<T>, CircularForwardIterator<T> {

    private val list: MutableList<T> = mutableListOf()
    private var currentPosition: Int = 0

    private var wasNext = false
    private var wasPrevious = false

    fun setCollection(collection: Collection<T>) {
        list.apply { clear(); list.addAll(collection) }
    }

    override fun isNotLatest(): Boolean = currentPosition < list.size

    override fun isNotFirst(): Boolean = currentPosition >= 0

    override fun getNext(): T {
        if (isNotLatest()) {

            if (wasPrevious) {
                currentPosition++
                currentPosition++
            }

            wasPrevious = false
            wasNext = true

            if (currentPosition >= list.size) resetToStart()

            return list[currentPosition++]

        } else {
            resetToStart()
            return list[currentPosition++]
        }
    }

    override fun getPrevious(): T {
        if (isNotFirst()) {

            if (wasNext) {
                currentPosition--
                currentPosition--
            }

            wasPrevious = true
            wasNext = false

            if (currentPosition <= -1) resetToEnd()

            return list[currentPosition--]

        } else {
            resetToEnd()
            return list[currentPosition--]
        }
    }

    override fun resetToStart() {
        currentPosition = 0
    }

    override fun resetToEnd() {
        currentPosition = list.size - 1
    }

    fun getSize(): Int = list.size
}