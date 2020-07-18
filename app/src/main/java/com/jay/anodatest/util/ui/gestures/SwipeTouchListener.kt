package com.jay.anodatest.util.ui.gestures

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs

open class SwipeTouchListener(ctx: Context?) : OnTouchListener {

    private val gestureDetector: GestureDetector = GestureDetector(ctx, GestureListener())

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeTop() {}
    open fun onSwipeBottom() {}

    companion object{
        const val SWIPE_RIGHT = 0
        const val SWIPE_LEFT = 1
        const val SWIPE_UP = 2
        const val SWIPE_DOWN = 3
    }

    private inner class GestureListener : SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onDown(e: MotionEvent): Boolean = true

        override fun onFling(motion1: MotionEvent,
                             motion2: MotionEvent,
                             velocityX: Float,
                             velocityY: Float): Boolean {

            var result = false

            try {
                val diffY: Float = motion2.y - motion1.y
                val diffX: Float = motion2.x - motion1.x

                if (abs(diffX) > abs(diffY)) {

                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {

                        if (diffX > 0) onSwipeRight() else onSwipeLeft()

                        result = true
                    }

                } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {

                    if (diffY > 0) onSwipeBottom() else onSwipeTop()

                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }
    }
}