package com.jay.anodatest.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class UserStories {

    @SerializedName("user_name")
    @Expose
    var userName: String? = null

    @SerializedName("user_image")
    @Expose
    var userImage: String? = null

    @SerializedName("place")
    @Expose
    var place: String? = null

    @SerializedName("images")
    @Expose
    var storyImages: List<String?>? = null

    @SerializedName("liked_by")
    @Expose
    var likedBy: List<String?>? = null

    @SerializedName("tags")
    @Expose
    var tags: List<String?>? = null

    @SerializedName("time_since_uploaded")
    @Expose
    var timeSinceUpload: String? = null
}