package com.jay.anodatest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.jay.anodatest.model.UserStories
import com.jay.anodatest.repository.UserStoriesRepository

class UserStoriesViewModel : BaseViewModel() {

    val userStoriesObserver: MutableLiveData<List<UserStories>> = MutableLiveData()

    private val userStoriesRepository = UserStoriesRepository()

    fun loadUserStories() {
        userStoriesObserver.postValue(userStoriesRepository.getUserStories())
    }
}