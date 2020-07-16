package com.jay.anodatest.util.common

import android.os.Build

object Constant {
    const val USER_STORIES_JSON_FILE_NAME = "userStories"
    val ABOVE_API_22 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

