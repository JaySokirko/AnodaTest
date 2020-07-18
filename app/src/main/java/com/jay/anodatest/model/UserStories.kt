package com.jay.anodatest.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class UserStories {

    @SerializedName("user_name")
    @Expose
    var userName: String? = null

    @SerializedName("user_image")
    @Expose
    var profileImage: String? = null

    @SerializedName("place")
    @Expose
    var place: String? = null

    @SerializedName("images")
    @Expose
    var storyImagesUrl: List<String?>? = null

    @SerializedName("liked_by")
    @Expose
    var likedBy: List<String?>? = null

    @SerializedName("comment")
    @Expose
    var tags: String? = null

    @SerializedName("time_since_uploaded")
    @Expose
    var timeSinceUpload: String? = null
}