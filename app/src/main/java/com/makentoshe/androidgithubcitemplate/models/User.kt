package com.makentoshe.androidgithubcitemplate.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar_url")
    val avatar: String
)