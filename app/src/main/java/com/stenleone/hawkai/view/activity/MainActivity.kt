package com.stenleone.hawkai.view.activity

import com.stenleone.hawkai.R
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.fragment.main.NewsFeedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    var homeFragment = NewsFeedFragment()

    private fun initBottomNavigation() {
        navigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {

                }
                R.id.navigation_photo -> {

                }
                R.id.navigation_search -> {

                }
                R.id.navigation_settings -> {

                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun initAfterCreate() {
        super.initAfterCreate()
        initBottomNavigation()
        fragmentManager.addFragmentToFragmentManager(homeFragment)
    }
}