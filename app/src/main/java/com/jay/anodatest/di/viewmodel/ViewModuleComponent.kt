package com.jay.anodatest.di.viewmodel

import androidx.fragment.app.FragmentActivity
import com.jay.anodatest.ui.activity.MainActivity
import com.jay.anodatest.ui.fragment.HomeFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModuleComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun fragmentActivity(fragmentActivity: FragmentActivity): Builder
        fun build() : ViewModuleComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)
}