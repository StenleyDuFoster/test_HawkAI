package com.stenleone.hawkai.view.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import com.stenleone.hawkai.R
import com.stenleone.hawkai.util.anim.LoadLeyAnimator
import com.stenleone.hawkai.util.shared_preferences.SharedPreferencesManager
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.fragment.login.LoginFragment
import kotlinx.android.synthetic.main.load_lay.*

class LoginActivity : BaseActivity(R.layout.activity_login) {

    private val loginFragment = LoginFragment()

    fun loadMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initAfterCreate() {
        super.initAfterCreate()

        if(SharedPreferencesManager.getToken() == null) {
            fragmentManager.addFragmentToFragmentManager(loginFragment)
        } else {
            loadMainActivity()
        }
    }
}