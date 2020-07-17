package com.jay.anodatest.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.jay.anodatest.R
import com.jay.anodatest.util.common.iterator.CircularIterator
import com.jay.anodatest.util.ui.gestures.SwipeTouchListener
import rx.subjects.PublishSubject

open class ImageViewer : FrameLayout {

    val swipeObserver: PublishSubject<Int> = PublishSubject.create()

    private val circularIterator = CircularIterator<Drawable>()

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private lateinit var rootLayout: ImageView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        init()
        setOnSwipeListener()
    }

    fun setImages(vararg images: Drawable) {
        circularIterator.setCollection(images.toList())
        rootLayout.setImageDrawable(circularIterator.getNext())
    }

    fun imageCount() = circularIterator.getSize()

    protected open fun setNextImage() {
        rootLayout.setImageDrawable(circularIterator.getNext())
    }

    protected open fun setPreviousImage() {
        rootLayout.setImageDrawable(circularIterator.getPrevious())
    }

    private fun init() {
        rootLayout = inflater.inflate(R.layout.view_image, this, false) as ImageView
        addView(rootLayout)
    }

    private fun setOnSwipeListener() {
        setOnTouchListener(object : SwipeTouchListener(context) {

            override fun onSwipeRight() {
                setPreviousImage()
                swipeObserver.onNext(SWIPE_RIGHT)
            }

            override fun onSwipeLeft() {
                setNextImage()
                swipeObserver.onNext(SWIPE_LEFT)
            }
        })
    }
}