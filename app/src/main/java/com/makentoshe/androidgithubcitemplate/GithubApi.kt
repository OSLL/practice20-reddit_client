package com.makentoshe.androidgithubcitemplate

import com.makentoshe.androidgithubcitemplate.models.Repos
import com.makentoshe.androidgithubcitemplate.models.User
import okhttp3.internal.http2.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApi {
    lateinit var api: Api
    public val ERROR_CONNECTION = 0
    public val ERROR_EMPTY = 1
    constructor(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com/")
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun getUserRepos(username: String, onReposListLoadCompleteListener: OnReposListLoadCompleteListener){
        api.getRepos(username).enqueue(object : Callback<List<Repos>> {
            override fun onResponse(call: Call<List<Repos>>, response: Response<List<Repos>>) {
                if(response.isSuccessful && response.body() != null){
                    if(response.body()!!.size > 0)
                        onReposListLoadCompleteListener.onSuccess(response.body()!!)
                    else
                        onReposListLoadCompleteListener.onFail(ERROR_EMPTY)
                } else onReposListLoadCompleteListener.onFail(response.code())
            }
            override fun onFailure(call: Call<List<Repos>>, t: Throwable) {
                onReposListLoadCompleteListener.onFail(ERROR_CONNECTION)
            }
        })
    }

    fun getUserInfo(username: String, onUserInfoLoadCompleteListener: OnUserInfoLoadCompleteListener){
        api.getInfo(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful && response.body() != null)
                    onUserInfoLoadCompleteListener.onSuccess(response.body()!!)
                else
                    onUserInfoLoadCompleteListener.onFail(response.code())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onUserInfoLoadCompleteListener.onFail(ERROR_CONNECTION)
            }
        })
    }

    interface OnUserInfoLoadCompleteListener{
        fun onSuccess(user: User)
        fun onFail(errorCode: Int)
    }
    interface  OnReposListLoadCompleteListener{
        fun onSuccess(repos: List<Repos>)
        fun onFail(errorCode: Int)
    }
}