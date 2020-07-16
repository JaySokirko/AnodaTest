package com.jay.anodatest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jay.anodatest.R
import com.jay.anodatest.di.viewmodel.DaggerViewModuleComponent
import com.jay.anodatest.ui.viewmodel.StoriesViewModel
import com.jay.anodatest.util.ui.SwipeTouchListener.Companion.SWIPE_LEFT
import com.jay.anodatest.util.ui.SwipeTouchListener.Companion.SWIPE_RIGHT
import kotlinx.android.synthetic.main.layout_user_story.*
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: StoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.layout_user_story, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        DaggerViewModuleComponent.builder().fragmentActivity(activity!!).build().inject(this)
        super.onActivityCreated(savedInstanceState)

        image_viewer.setImages(
            resources.getDrawable(R.drawable.profile_image),
            resources.getDrawable(R.drawable.profile_image_2),
            resources.getDrawable(R.drawable.profile_image_3))

        dots_view.setDots(image_viewer.imageCount())

        image_viewer.swipeObserver
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it){
                    SWIPE_RIGHT -> {
                        dots_view.highlightPreviousDot()
                    }
                    SWIPE_LEFT -> {
                        dots_view.highlightNextDot()
                    }
                }
            }

        initBinding()
    }

    fun initBinding() {

    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}