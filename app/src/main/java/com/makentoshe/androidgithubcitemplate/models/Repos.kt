package com.makentoshe.androidgithubcitemplate.models

import com.google.gson.annotations.SerializedName

data class Repos(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("private")
    var private: Boolean,
    @SerializedName("owner")
    var owner: User,
    @SerializedName("url")
    var url: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("updated_at")
    var updated_at: String,
    @SerializedName("language")
    var language: String
)