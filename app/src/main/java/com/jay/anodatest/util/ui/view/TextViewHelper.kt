package com.jay.anodatest.util.ui.view

import android.graphics.Rect
import android.text.Layout
import android.widget.TextView
import kotlinx.coroutines.*

class TextViewHelper {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    suspend fun getVisibleText(textView: TextView): String? {
        val rect = Rect()
        textView.getHitRect(rect) //get visible rect of TextView

        val layout: Layout? = try {
            textView.getLayoutWhenAppear()
        } catch (e: IllegalThreadStateException) {
            e.printStackTrace()
            null
        }

        layout ?: return null

        val line: Int = layout.getLineForVertical(-1 * rect.top) //first visible line
        val start: Int = layout.getLineStart(line) //visible line start
        val end: Int = layout.getLineEnd(line) //visible line end

        return textView.text.toString().substring(start, end)
    }

    @Throws(exceptionClasses = [IllegalThreadStateException::class])
    suspend fun TextView.getLayoutWhenAppear(timeOut: Long = 100): Layout {

        var timeOutCounter: Long = timeOut

        val async: Deferred<Layout> = coroutineScope.async {

            while (this@getLayoutWhenAppear.layout == null) {
                delay(timeMillis = 1)
                if (timeOutCounter-- <= 0) throw IllegalThreadStateException("time out")
            }
            this@getLayoutWhenAppear.layout
        }
        return async.await()
    }

    fun clear() {
        coroutineScope.cancel()
    }
}
