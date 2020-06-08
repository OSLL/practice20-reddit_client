package com.makentoshe.androidgithubcitemplate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.models.DirObject
import java.text.DecimalFormat

class FileViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {
    val TYPE_FILE: Int = 1
    val TYPE_FOLDER: Int = 0



    class FileViewHolder: RecyclerView.ViewHolder{
        constructor(itemView: View) : super(itemView){
            nameView = itemView.findViewById(R.id.nameView)
            sizeView = itemView.findViewById(R.id.sizeView)
        }

        var nameView: TextView
        var sizeView: TextView

        fun setContent(content: DirObject){
            nameView.setText(content.name)
            sizeView.setText(getSizeString(content.size))
        }

        fun getSizeString(size: Long): String? {
            val df = DecimalFormat("0.00")
            val sizeKb = 1024.0f
            val sizeMb = sizeKb * sizeKb
            val sizeGb = sizeMb * sizeKb
            val sizeTerra = sizeGb * sizeKb
            if (size < sizeMb) return df.format(size / sizeKb)
                .toString() + " Kb" else if (size < sizeGb) return df.format(size / sizeMb)
                .toString() + " Mb" else if (size < sizeTerra) return df.format(size / sizeGb)
                .toString() + " Gb"
            return ""
        }
    }

    class FolderViewHolder: RecyclerView.ViewHolder{
        constructor(itemView: View) : super(itemView) {
            nameView = itemView.findViewById(R.id.nameView)
        }

        var nameView: TextView

        fun setContent(content: DirObject){
            nameView.setText(content.name)
        }
    }

    val list: MutableList<DirObject>

    constructor(){
        list = ArrayList<DirObject>()
    }

    override fun getItemViewType(position: Int): Int {
        if(list.get(position).type.equals("dir"))
            return TYPE_FOLDER
        return TYPE_FILE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_FOLDER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_item, parent, false)
            return FolderViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return FileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == TYPE_FOLDER)
            (holder as FolderViewHolder).setContent(list.get(position))
        else
            (holder as FileViewHolder).setContent(list.get(position))
    }

    fun setList(list: List<DirObject>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }

    fun getObject(position: Int): DirObject{
        return list.get(position)
    }
}