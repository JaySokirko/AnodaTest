package com.jay.anodatest

import androidx.multidex.MultiDexApplication
import com.jay.anodatest.di.base.BaseComponent
import com.jay.anodatest.di.base.BaseModule
import com.jay.anodatest.di.base.DaggerBaseComponent

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        baseComponent = DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
        super.onCreate()
    }

    companion object {
        lateinit var baseComponent: BaseComponent
    }
}