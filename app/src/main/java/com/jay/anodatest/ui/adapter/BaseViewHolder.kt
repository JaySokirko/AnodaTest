package com.jay.anodatest.ui.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jay.anodatest.BaseApplication

abstract class BaseViewHolder<in T>(itemView: View): RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

    protected val applicationContext: Context =
        BaseApplication.baseComponent.application.applicationContext
}