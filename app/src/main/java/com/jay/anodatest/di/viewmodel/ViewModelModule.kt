package com.jay.anodatest.di.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.jay.anodatest.ui.viewmodel.StoriesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideUserStoriesModule(fragmentActivity: FragmentActivity): StoriesViewModel {
        return ViewModelProviders.of(fragmentActivity).get(StoriesViewModel::class.java)
    }
}