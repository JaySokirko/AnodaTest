package com.jay.anodatest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.anodatest.R
import com.jay.anodatest.di.viewmodel.DaggerViewModuleComponent
import com.jay.anodatest.model.UserStories
import com.jay.anodatest.ui.adapter.UserStoriesAdapter
import com.jay.anodatest.ui.viewmodel.UserStoriesViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeFragment : BaseFragment() {

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

        userStoriesVM.loadUserStories()

        setupUserStoriesRecyclerView()
        observeUserStories()

        lifecycle.addObserver(userStoriesAdapter)
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