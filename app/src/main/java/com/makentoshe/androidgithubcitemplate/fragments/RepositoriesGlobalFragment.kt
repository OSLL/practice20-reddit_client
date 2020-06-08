package com.makentoshe.androidgithubcitemplate.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.makentoshe.androidgithubcitemplate.GithubApi
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.RecycleItemClickListener
import com.makentoshe.androidgithubcitemplate.activities.FilesActivity
import com.makentoshe.androidgithubcitemplate.adapters.ReposViewAdapter
import com.makentoshe.androidgithubcitemplate.models.Repos


class RepositoriesGlobalFragment: Fragment {
    constructor() : super()
    lateinit var githubApi: GithubApi
    lateinit var  recyclerView: RecyclerView
    lateinit var adapter : ReposViewAdapter
    lateinit var progressbar: ProgressBar
    lateinit var infoView: TextView

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
        infoView = view.findViewById(R.id.infoView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        (view.findViewById<Toolbar>(R.id.toolbar).menu.findItem(R.id.action_project_search).actionView as SearchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    loadRepos(query!!)
                    return true
                }
            })
        recyclerView.addOnItemTouchListener(
            RecycleItemClickListener(context, recyclerView,
                object : RecycleItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        openRepo(adapter.getRepo(position))
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )
        return view
    }

    fun openRepo(repo: Repos){
        val intent = Intent(activity, FilesActivity::class.java)

        intent.putExtra("id",repo.id)
        intent.putExtra("path", "")
        startActivity(intent)
    }

    fun loadRepos(query: String){
        if(query.isNullOrEmpty())
            return
        progressbar.visibility = View.VISIBLE
        adapter.clear()
        infoView.visibility = View.GONE
        githubApi.searchRepos(query, object : GithubApi.OnReposListLoadCompleteListener {
            override fun onFail(errorCode: Int) {
                Snackbar.make(progressbar, "some errors happened:" + errorCode, Snackbar.LENGTH_LONG).show()
                progressbar.visibility = View.GONE
                infoView.visibility = View.VISIBLE
                infoView.setText("Error:" + errorCode)
            }

            override fun onSuccess(repos: List<Repos>) {
                progressbar.visibility = View.GONE
                adapter.setList(repos)
            }
        })
    }
}