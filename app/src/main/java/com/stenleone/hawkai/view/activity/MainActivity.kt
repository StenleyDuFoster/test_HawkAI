package com.stenleone.hawkai.view.activity

import com.stenleone.hawkai.R
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private fun initBottomNavigation () {
        navigationView.setOnNavigationItemSelectedListener {
            makeToast(it.itemId.toString())

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun initAfterCreate() {
        super.initAfterCreate()
        initBottomNavigation()
    }
}