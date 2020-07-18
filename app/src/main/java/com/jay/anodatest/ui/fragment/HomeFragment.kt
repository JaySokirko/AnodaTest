package com.jay.anodatest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.anodatest.R
import com.jay.anodatest.di.viewmodel.DaggerViewModuleComponent
import com.jay.anodatest.model.UserStories
import com.jay.anodatest.ui.adapter.UserStoriesAdapter
import com.jay.anodatest.ui.viewmodel.UserStoriesViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var userStoriesVM: UserStoriesViewModel

    private val userStoriesAdapter = UserStoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        DaggerViewModuleComponent.builder().fragmentActivity(activity!!).build().inject(this)
        super.onActivityCreated(savedInstanceState)

//        image_viewer.setImages(
//            resources.getDrawable(R.drawable.profile_image),
//            resources.getDrawable(R.drawable.profile_image_2),
//            resources.getDrawable(R.drawable.profile_image_3)
//        )
//
//        dots_view.setDots(image_viewer.imageCount())
//
//        image_viewer.swipeObserver
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                when (it) {
//                    SWIPE_RIGHT -> {
//                        dots_view.highlightPreviousDot()
//                    }
//                    SWIPE_LEFT -> {
//                        dots_view.highlightNextDot()
//                    }
//                }
//            }
//
//        val spannableString: SpannableStringBuilder =
//            "#asd dddghhh #hbhfghhb #sdff @werwe aaa !qqq"
//                .highlightLabeledText(resources.getColor(R.color.blue_400), "#", "@", "!")
//
//        comment_content.setText("\uD83D\uDC99 \uD83C\uDF0F \uD83D\uDCAB")
//
//        val textToDisplay =
//            "text1 text2 text_text1 text_text_text3 text4 text_text5 text6_text_text"
//
//        liked_by_list.text = textToDisplay
//
//        val textViewHelper = TextViewHelper()
//
//        GlobalScope.launch {
//            val text = textViewHelper.getVisibleText(liked_by_list)
//            Log.d("TAG", "onActivityCreated: " + text)
//            val completedText: Pair<Int, String>? = text?.splitTextByChar(",")
//            Log.d("TAG", "onActivityCreated: " + completedText?.first)
//            Log.d("TAG", "onActivityCreated: " + completedText?.second)
//        }
//
//        Glide.with(this)
//            .load("https://image.freepik.com/free-photo/_155003-6455.jpg")
//            .error(R.drawable.ic_circle_red)
//            .override(200, 200)
//            .into(profile_image)

        userStoriesVM.loadUserStories()

        setupUserStoriesRecyclerView()
        observeUserStories()

        initBinding()
    }

    fun initBinding() {

    }

    private fun setupUserStoriesRecyclerView() {
        user_stories_recycler_view.setHasFixedSize(true)
        user_stories_recycler_view.layoutManager = LinearLayoutManager(context)
        user_stories_recycler_view.adapter = userStoriesAdapter
    }

    private fun observeUserStories() {
        userStoriesVM.userStoriesObserver.observe(viewLifecycleOwner, Observer { result: List<UserStories?> ->
            userStoriesAdapter.setData(result)
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}