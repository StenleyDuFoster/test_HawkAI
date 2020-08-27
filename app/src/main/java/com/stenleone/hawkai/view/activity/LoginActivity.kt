package com.stenleone.hawkai.view.activity

import android.content.Intent
import com.stenleone.hawkai.R
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.fragment.login.LoginFragment

class LoginActivity : BaseActivity(R.layout.activity_login) {

    val loginFragment = LoginFragment()

    fun loadMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initAfterCreate() {
        fragmentManager.addFragmentToFragmentManager(loginFragment)
    }
}