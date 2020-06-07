package com.makentoshe.androidgithubcitemplate.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.makentoshe.androidgithubcitemplate.GithubApi
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.adapters.ReposViewAdapter
import com.makentoshe.androidgithubcitemplate.models.Repos


class RepositoriesFragment: Fragment {
    constructor() : super()
    lateinit var githubApi: GithubApi
    lateinit var  recyclerView: RecyclerView
    lateinit var adapter : ReposViewAdapter
    lateinit var progressbar: ProgressBar
    lateinit var sp: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.repositories_layout, container, false)

        githubApi = GithubApi()
        recyclerView = view.findViewById(R.id.reposList)
        adapter = ReposViewAdapter()
        progressbar = view.findViewById(R.id.progressBar)
        sp = context!!.getSharedPreferences("com.makentoshe.androidgithubcitemplate_preferences", Context.MODE_PRIVATE)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        loadRepos()
        return view
    }
    fun loadRepos(){
        if(sp.getString("user", "").isNullOrEmpty())
            return
        progressbar.visibility = View.VISIBLE
        adapter.clear()
        githubApi.getUserRepos(sp.getString("user", "Wiselogias")!!, object : GithubApi.OnReposListLoadCompleteListener {
            override fun onFail(errorCode: Int) {
                Snackbar.make(progressbar, "some errors happened:" + errorCode, Snackbar.LENGTH_LONG).show()
                progressbar.visibility = View.GONE
            }

            override fun onSuccess(repos: List<Repos>) {
                progressbar.visibility = View.GONE
                adapter.setList(repos)
            }
        })
    }
}