package com.jay.anodatest.repository

import com.google.gson.reflect.TypeToken
import com.jay.anodatest.model.UserStories
import com.jay.anodatest.util.common.Constant.USER_STORIES_JSON_FILE_NAME
import com.jay.anodatest.util.common.json.JsonParser

class UserStoriesRepository {

    private val jsonParser = JsonParser<UserStories>()

    fun getUserStories(): List<UserStories>? =
        jsonParser.getParsedObject(USER_STORIES_JSON_FILE_NAME,
            object : TypeToken<List<UserStories>>() {})
}