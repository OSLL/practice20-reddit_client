package com.makentoshe.androidgithubcitemplate.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.makentoshe.androidgithubcitemplate.GithubApi
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.RecycleItemClickListener
import com.makentoshe.androidgithubcitemplate.adapters.FileViewAdapter
import com.makentoshe.androidgithubcitemplate.models.DirObject
import kotlinx.android.synthetic.main.activity_files.*

class FilesActivity : AppCompatActivity() {
    lateinit var githubApi: GithubApi
    lateinit var adapter: FileViewAdapter
    var id: Int = 0
    lateinit var path: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files)

        githubApi = GithubApi()
        adapter = FileViewAdapter()

        path = intent.getStringExtra("path")
        id = intent.getIntExtra("id",82128465)


        toolbar.setTitle(path + "/")
        filesView.setHasFixedSize(true)
        filesView.layoutManager = LinearLayoutManager(this)
        filesView.adapter = adapter
        filesView.addOnItemTouchListener(RecycleItemClickListener(this, filesView,
            object : RecycleItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    if(adapter.getItemViewType(position) == adapter.TYPE_FOLDER)
                      openFolder(adapter.getObject(position))
                }

                override fun onLongItemClick(view: View?, position: Int) {
                }
            }))
        loadContent()
    }

    fun openFolder(folder: DirObject){
        val intent = Intent(this, FilesActivity::class.java)
        intent.putExtra("path", "/" + folder.name)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun loadContent(){
        progressBar.visibility = View.VISIBLE
        githubApi.getContent(id, path, object : GithubApi.OnContentLoadCompleteListener {
            override fun onSuccess(files: List<DirObject>) {
                Log.println(Log.INFO, "LOL", files.toString())
                progressBar.visibility = View.GONE
                adapter.setList(files)
            }

            override fun onFail(errorCode: Int) {
                Log.println(Log.INFO, "LOL", "Error: " + errorCode)
                progressBar.visibility = View.GONE
            }
        })
    }
}
