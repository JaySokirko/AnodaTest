package com.jay.anodatest.ui.fragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jay.anodatest.R
import com.jay.anodatest.di.viewmodel.DaggerViewModuleComponent
import com.jay.anodatest.ui.viewmodel.StoriesViewModel
import com.jay.anodatest.util.splitTextByChar
import com.jay.anodatest.util.highlightLabeledText
import com.jay.anodatest.util.ui.gestures.SwipeTouchListener.Companion.SWIPE_LEFT
import com.jay.anodatest.util.ui.gestures.SwipeTouchListener.Companion.SWIPE_RIGHT
import com.jay.anodatest.util.ui.view.TextViewHelper
import kotlinx.android.synthetic.main.layout_user_story.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

        dots_view.setDots(20)

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

        val spannableString: SpannableStringBuilder =
            "#asd dddghhh #hbhfghhb #sdff @werwe aaa !qqq"
                .highlightLabeledText(resources.getColor(R.color.blue_400), "#", "@", "!")

        comment_content.setText(spannableString, TextView.BufferType.SPANNABLE)

        val textToDisplay = "text1 text2 text_text1 text_text_text3 text4 text_text5 text6_text_text"

        liked_by_list.text = textToDisplay

        val textViewHelper = TextViewHelper()

        GlobalScope.launch {
            val text = textViewHelper.getVisibleText(liked_by_list)
            Log.d("TAG", "onActivityCreated: " + text)
            val completedText: Pair<Int, String>? = text?.splitTextByChar(",")
            Log.d("TAG", "onActivityCreated: " + completedText?.first)
            Log.d("TAG", "onActivityCreated: " + completedText?.second)
        }

        initBinding()
    }

    fun initBinding() {

    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}