package com.jay.anodatest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.jay.anodatest.R
import com.jay.anodatest.util.common.iterator.CircularIterator
import de.hdodenhof.circleimageview.CircleImageView

open class DotsView : LinearLayout {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val circularIterator = CircularIterator<Int>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    fun setDots(count: Int) {
        val list: MutableList<Int> = mutableListOf()

        repeat(count) { index: Int ->
            addView(inflateCircleView())
            list.add(index)
        }
        circularIterator.setCollection(list)
        highlightDotAtPosition(circularIterator.getNext())
    }

    fun highlightNextDot() {
        resetHighlighting()
        highlightDotAtPosition(circularIterator.getNext())
    }

    fun highlightPreviousDot() {
        resetHighlighting()
        highlightDotAtPosition(circularIterator.getPrevious())
    }

    private fun highlightDotAtPosition(position: Int) {
        val circleImageView: CircleImageView = (getChildAt(position)) as CircleImageView
        circleImageView.setImageResource(R.color.blue_400)
    }

    private fun resetHighlighting() {
        getAllCircleImageView().forEach { it.setImageResource(R.color.gray_400) }
    }

    private fun getAllCircleImageView(): MutableList<CircleImageView> {
        val childList: MutableList<CircleImageView> = mutableListOf()

        (0 until childCount).forEach { index ->
            val child: View = getChildAt(index)
            if (child is CircleImageView) childList.add(child)
        }
       return childList
    }

    private fun inflateCircleView(): CircleImageView =
        inflater.inflate(R.layout.view_circle_image, this, false) as CircleImageView
}