package com.jay.anodatest.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.jay.anodatest.R
import com.jay.anodatest.util.ui.SwipeTouchListener

class ImageViewer : FrameLayout {

    private val imagesContainer: MutableList<Drawable> = mutableListOf()

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private lateinit var rootLayout: ImageView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        init()
//        setOnSwipeListener()
    }

    private fun init() {
        rootLayout = inflater.inflate(R.layout.view_image_viewer, this, false) as ImageView
        addView(rootLayout)

        rootLayout.setImageDrawable(resources.getDrawable(R.drawable.profile_image))
    }

     fun setOnSwipeListener(context: Context) {
        setOnTouchListener(object : SwipeTouchListener(context) {

            override fun onSwipeRight() {
                Log.d("TAG", "onSwipeRight: ")
            }

            override fun onSwipeLeft() {
                Log.d("TAG", "onSwipeLeft: ")
            }
        })
    }

}