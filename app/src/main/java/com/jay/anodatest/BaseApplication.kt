package com.jay.anodatest

import android.app.Application
import com.jay.currencyconverter.di.base.BaseComponent
import com.jay.currencyconverter.di.base.BaseModule
import com.jay.currencyconverter.di.base.DaggerBaseComponent

class BaseApplication : Application() {

    override fun onCreate() {
        baseComponent = DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
        super.onCreate()
    }

    companion object {
        lateinit var baseComponent: BaseComponent
    }
}