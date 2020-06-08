package com.makentoshe.androidgithubcitemplate.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.makentoshe.androidgithubcitemplate.activities.MainActivity
import com.makentoshe.androidgithubcitemplate.R

class LoginFragment: Fragment {
    constructor() : super()
    lateinit var sp: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_layout, container, false)
        sp = view.context.getSharedPreferences("com.makentoshe.androidgithubcitemplate_preferences", Context.MODE_PRIVATE)
        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            saveUserName(view.findViewById<TextInputEditText>(R.id.loginText).text.toString())
            (activity as MainActivity).setFragment(AccountFragment())
        }
        return view
    }
    fun saveUserName(name: String){
        val editor = sp.edit()
        editor.putString("user", name)
        editor.apply()
    }
}