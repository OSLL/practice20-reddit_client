package com.makentoshe.androidgithubcitemplate.models

import com.google.gson.annotations.SerializedName

data class DirObject (
    @SerializedName("name")
    var name: String,
    @SerializedName("path")
    var path: String,
    @SerializedName("sha")
    var sha: String,
    @SerializedName("size")
    var size: Long,
    @SerializedName("download_url")
    var download_url: String,
    @SerializedName("type")
    var type: String
)
