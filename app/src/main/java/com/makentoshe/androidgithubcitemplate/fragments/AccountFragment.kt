package com.makentoshe.androidgithubcitemplate.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.makentoshe.androidgithubcitemplate.GithubApi
import com.makentoshe.androidgithubcitemplate.MainActivity
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.SettingsActivity
import com.makentoshe.androidgithubcitemplate.models.User
import kotlinx.android.synthetic.main.profile_layout.*

class AccountFragment : Fragment {
    constructor() : super()
    lateinit var sp: SharedPreferences
    lateinit var username: String
    lateinit var githubApi: GithubApi
    lateinit var profileView: ImageView
    lateinit var emailView: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.profile_layout, container, false)
        sp = view.context.getSharedPreferences("com.makentoshe.androidgithubcitemplate_preferences", Context.MODE_PRIVATE)
        githubApi = GithubApi()
        profileView = view.findViewById(R.id.avatar)
        emailView = view.findViewById(R.id.emailText)
        username = sp.getString("user", "Octocat").orEmpty()

        view.findViewById<TextInputEditText>(R.id.login).setText(username)
        (view.findViewById<ImageView>(R.id.settingsButton)).setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.changeAccountButton).setOnClickListener {
            logout()
            (activity as MainActivity).setFragment(LoginFragment())
        }
        view.findViewById<ImageView>(R.id.notificationButton).setOnClickListener {
            var bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, BottomSheetFragment::class.simpleName)
        }

        view.findViewById<Button>(R.id.repositoriesButton).setOnClickListener {
            (activity as MainActivity).setFragment(RepositoriesFragment())
        }
        view.findViewById<Button>(R.id.projectsButton).setOnClickListener {
            Snackbar.make(it, "Sorry GitHub is working with Project's API", Snackbar.LENGTH_LONG).show()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        loadUserInfo()
    }
    fun logout(){
        val editor = sp.edit()
        editor.putString("user", "")
        editor.apply()
    }
    fun loadUserInfo(){
        githubApi.getUserInfo(username, object : GithubApi.OnUserInfoLoadCompleteListener {
            override fun onSuccess(user: User) {
                if(user.email.isNullOrEmpty())
                    emailView.setText("not specified")
                else
                  emailView.setText(user.email)
                Glide
                    .with(profileView.context.applicationContext)
                    .load(user.avatar)
                    .circleCrop()
                    .into(profileView)
            }

            override fun onFail(errorCode: Int) {
                Snackbar.make(profileView, "some errors happened or there is no user with this login" + errorCode, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}