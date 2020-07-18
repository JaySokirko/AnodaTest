package com.jay.anodatest.ui.viewmodel

import android.widget.Toast

class BottomNavigationViewModel : BaseViewModel() {

    fun onHomeClick() {
        Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
    }

    fun onSearchClick() {
        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
    }

    fun onAddContent() {
        Toast.makeText(context, "Add content", Toast.LENGTH_SHORT).show()
    }

    fun onFavorite() {
        Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
    }

    fun onProfile() {
        Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
    }
}