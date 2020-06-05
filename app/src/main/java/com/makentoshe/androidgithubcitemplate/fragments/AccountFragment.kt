package com.makentoshe.androidgithubcitemplate.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.xml.R
import com.makentoshe.androidgithubcitemplate.SettingsActivity
import kotlinx.android.synthetic.main.profile_layout.*

class AccountFragment : Fragment {
    constructor() : super()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.profile_layout, container, false)
        (view.findViewById<ImageView>(R.id.settingsButton)).setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<ImageView>(R.id.notificationButton).setOnClickListener {
            var bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, BottomSheetFragment::class.simpleName)
        }
        return view
    }
}