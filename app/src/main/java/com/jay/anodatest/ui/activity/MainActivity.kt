package com.jay.anodatest.ui.activity

import android.os.Bundle
import android.util.Log
import com.jay.anodatest.R
import com.jay.anodatest.repository.UserStoriesRepository
import com.jay.anodatest.ui.activity.BaseActivity
import com.jay.anodatest.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment.newInstance())
            .commit()
    }
}