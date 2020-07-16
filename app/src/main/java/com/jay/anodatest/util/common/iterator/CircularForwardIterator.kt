package com.jay.anodatest.util.common.iterator

interface CircularForwardIterator<T> {

    /**
     * Checks that the current item is not the latest in the collection.
     */
    fun isNotLatest(): Boolean

    /**
     * Get a next item from the collection
     */
    fun getNext(): T

    /**
     * If an iterator position achieves the latest element in the collection,
     * then the iterator position must be set to the very beginning of the collection.
     * Thus, the iteration of the elements in the collection is in the circle.
     */
    fun resetToStart()
}