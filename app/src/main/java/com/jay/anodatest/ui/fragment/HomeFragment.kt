package com.jay.anodatest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jay.anodatest.R
import com.jay.anodatest.di.viewmodel.DaggerViewModuleComponent
import com.jay.anodatest.ui.viewmodel.StoriesViewModel
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

        initBinding()
    }

    fun initBinding() {

    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}