package com.jay.anodatest.util.common.diffUtil

import com.jay.anodatest.model.UserStories

class UserStoriesDiffUtil : BaseDiffUtil<UserStories>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkContent(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return checkContent(oldItemPosition, newItemPosition)
    }

    private fun checkContent(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].likedBy == newList[newItemPosition].likedBy &&
                oldList[oldItemPosition].place == newList[newItemPosition].place &&
                oldList[oldItemPosition].timeSinceUpload == newList[newItemPosition].timeSinceUpload
    }
}