package com.jay.anodatest.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jay.anodatest.R
import com.jay.anodatest.util.common.Constant.ABOVE_API_22

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @SuppressLint("NewApi")
        if (ABOVE_API_22) {
            window.statusBarColor = getColor(R.color.gray_50)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}