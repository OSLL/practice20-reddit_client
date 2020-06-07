package com.makentoshe.androidgithubcitemplate

import com.makentoshe.androidgithubcitemplate.models.Repos
import com.makentoshe.androidgithubcitemplate.models.SearchResult
import com.makentoshe.androidgithubcitemplate.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET ("users/{username}")
    fun getInfo(@Path(value = "username", encoded = false) username: String): Call<User>

    @GET ("users/{username}/repos")
    fun getRepos(@Path(value = "username", encoded = false) username: String): Call<List<Repos>>

    @GET ("search/repositories")
    fun searchRepos(@Query("q") query: String): Call<SearchResult>
}