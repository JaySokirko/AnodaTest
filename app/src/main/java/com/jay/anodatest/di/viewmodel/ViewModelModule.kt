package com.jay.anodatest.di.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.jay.anodatest.ui.viewmodel.BottomNavigationViewModel
import com.jay.anodatest.ui.viewmodel.TopNavigationViewModel
import com.jay.anodatest.ui.viewmodel.UserStoriesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideUserStoriesModule(fragmentActivity: FragmentActivity): UserStoriesViewModel {
        return ViewModelProviders.of(fragmentActivity).get(UserStoriesViewModel::class.java)
    }

    @Provides
    fun provideBottomNavigationViewModel(fragmentActivity: FragmentActivity): BottomNavigationViewModel {
        return ViewModelProviders.of(fragmentActivity).get(BottomNavigationViewModel::class.java)
    }

    @Provides
    fun provideTopNavigationViewModedl(fragmentActivity: FragmentActivity) : TopNavigationViewModel {
        return ViewModelProviders.of(fragmentActivity).get(TopNavigationViewModel::class.java)
    }
}