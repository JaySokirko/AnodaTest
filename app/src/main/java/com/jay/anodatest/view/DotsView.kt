package com.jay.anodatest.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.jay.anodatest.R
import de.hdodenhof.circleimageview.CircleImageView

class DotsView : FrameLayout {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private lateinit var rootLayout: LinearLayout
    private lateinit var circleImage: CircleImageView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        init()
    }

    private fun init() {
        rootLayout = inflater.inflate(R.layout.view_image_viewer, this, false) as LinearLayout
        addView(rootLayout)

        circleImage = inflater.inflate(R.layout.view_circle, this, false) as CircleImageView
    }
}