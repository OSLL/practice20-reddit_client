package com.makentoshe.androidgithubcitemplate


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.makentoshe.androidgithubcitemplate.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var sp = getSharedPreferences("com.makentoshe.androidgithubcitemplate_preferences", Context.MODE_PRIVATE)
        val isNight: Boolean = sp.getBoolean("dark_theme_key", false)
        AppCompatDelegate.setDefaultNightMode(if (isNight) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            fragmentSelector(it.itemId)
            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun onResume() {
        super.onResume()
        fragmentSelector(bottom_nav_bar.selectedItemId)
    }
    fun fragmentSelector(id : Int){
        var fragment: Fragment = AccountFragment()
        when(id){
            R.id.action_profile -> {
                fragment = AccountFragment()
            }
            R.id.action_editor -> {
                fragment = EditorFragment()
            }
            R.id.action_messager -> {
                fragment = ChatFragment()
            }
            R.id.action_projects -> {
                fragment = ProjectsFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
    public fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame, fragment).commit()
    }
}

