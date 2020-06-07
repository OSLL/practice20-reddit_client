package com.makentoshe.androidgithubcitemplate

import com.makentoshe.androidgithubcitemplate.models.Repos
import com.makentoshe.androidgithubcitemplate.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET ("users/{username}")
    fun getInfo(@Path(value = "username", encoded = false) username: String): Call<User>

    @GET ("users/{username}/repos")
    fun getRepos(@Path(value = "username", encoded = false) username: String): Call<List<Repos>>
}