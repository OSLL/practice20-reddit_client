package com.makentoshe.androidgithubcitemplate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.models.Repos

class ReposViewAdapter: RecyclerView.Adapter<ReposViewAdapter.ReposViewHolder> {

    class ReposViewHolder: RecyclerView.ViewHolder{

        constructor(itemView: View) : super(itemView){
            nameView = itemView.findViewById(R.id.nameView)
            dateView = itemView.findViewById(R.id.dateView)
            langView = itemView.findViewById(R.id.langView)
            ownerView = itemView.findViewById(R.id.ownerView)
        }

        val nameView: TextView
        val dateView: TextView
        val langView: TextView
        val ownerView: TextView

        fun setRepos(repos: Repos){
            nameView.setText(repos.name)
            dateView.setText(repos.updated_at.substring(0, 10))
            langView.setText(repos.language)
            ownerView.setText(repos.owner.login)
        }
    }
    val list: MutableList<Repos>
    constructor() : super(){
        list = ArrayList<Repos>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repos_element, parent, false)
        return ReposViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.setRepos(list.get(position))
    }
    fun setList(list: List<Repos>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }
}