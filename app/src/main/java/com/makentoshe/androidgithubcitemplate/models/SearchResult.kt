package com.makentoshe.androidgithubcitemplate.models

import com.google.gson.annotations.SerializedName

data class SearchResult (
    @SerializedName("items")
    var items: List<Repos>
)