package com.jay.anodatest.util.common.iterator

interface CircularBackwardIterator<T> {

    /**
     * Checks that the current item is not the very first in the collection.
     */
    fun isNotFirst(): Boolean

    /**
     * Get a previous item from the collection
     */
    fun getPrevious(): T

    /**
     * If an iterator position achieves the first element in the collection,
     * then the iterator position must be set to the very end of the collection.
     * Thus, the iteration of the elements in the collection is in the circle.
     */
    fun resetToEnd()
}