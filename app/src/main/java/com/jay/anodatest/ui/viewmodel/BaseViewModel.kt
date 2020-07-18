package com.jay.anodatest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.jay.anodatest.BaseApplication
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()
    protected val context: Context = BaseApplication.baseComponent.application.applicationContext

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}