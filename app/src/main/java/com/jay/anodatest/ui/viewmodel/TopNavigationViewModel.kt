package com.jay.anodatest.ui.viewmodel

import android.widget.Toast

class TopNavigationViewModel : BaseViewModel() {

    fun onOpenCamera() {
        Toast.makeText(context, "Open camera", Toast.LENGTH_SHORT).show()
    }

    fun onSend() {
        Toast.makeText(context, "Send", Toast.LENGTH_SHORT).show()
    }
}