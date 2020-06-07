package com.makentoshe.androidgithubcitemplate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.makentoshe.androidgithubcitemplate.R


class ProjectsFragment : Fragment {
    constructor() : super()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.projects_layout, container, false)
        return view
    }
}