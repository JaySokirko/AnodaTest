package com.jay.anodatest.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jay.anodatest.R
import com.jay.anodatest.databinding.ActivityMainBinding
import com.jay.anodatest.di.viewmodel.DaggerViewModuleComponent
import com.jay.anodatest.ui.fragment.HomeFragment
import com.jay.anodatest.ui.viewmodel.BottomNavigationViewModel
import com.jay.anodatest.ui.viewmodel.TopNavigationViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var bottomNavViewModel: BottomNavigationViewModel

    @Inject
    lateinit var topNavViewModel: TopNavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerViewModuleComponent.builder().fragmentActivity(this).build().inject(this)
        super.onCreate(savedInstanceState)
        initBinding()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment.newInstance())
            .commit()
    }

    private fun initBinding() {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavVM = bottomNavViewModel
        binding.topNavVm = topNavViewModel
    }
}