package com.stenleone.hawkai.view.activity

import com.stenleone.hawkai.R
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.fragment.LoginFragment

class LoginActivity : BaseActivity(R.layout.activity_login) {

    val loginFragment = LoginFragment()

    override fun initAfterCreate() {
        fragmentManager.addFragmentToFragmentManager(loginFragment)
    }
}