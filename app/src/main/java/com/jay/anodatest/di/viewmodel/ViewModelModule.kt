package com.jay.anodatest.di.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.jay.anodatest.ui.viewmodel.UserStoriesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideUserStoriesModule(fragmentActivity: FragmentActivity): UserStoriesViewModel {
        return ViewModelProviders.of(fragmentActivity).get(UserStoriesViewModel::class.java)
    }
}