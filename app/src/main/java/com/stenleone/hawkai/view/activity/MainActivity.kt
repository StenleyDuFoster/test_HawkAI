package com.stenleone.hawkai.view.activity

import android.content.pm.PackageManager
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.R
import com.stenleone.hawkai.util.constant.IntentConstatnt
import com.stenleone.hawkai.util.easyInfo.l
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.fragment.additionals.CommentsFragment
import com.stenleone.hawkai.view.fragment.main.NewsFeedFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(R.layout.activity_main) {

    var homeFragment = NewsFeedFragment()
    lateinit var commentsFragment: CommentsFragment

    fun showBackButton() {
        backClick.visibility = View.VISIBLE
    }

    fun hideNavBar() {
        navigationView.visibility = View.GONE
    }

    private fun initBackClick() {
        disposable.add(
            backClick.clicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe{
                    onBackPressed()
                }
        )
    }

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
        initBackClick()
        fragmentManager.addFragmentToFragmentManager(homeFragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backClick.visibility = View.INVISIBLE
        navigationView.visibility = View.VISIBLE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            IntentConstatnt.CAMERA -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    commentsFragment.let {
                        it.intentMediaManager.createCameraWithPermission()
                    }
                } else {
                    makeToast("no access to the camera")
                }
                return
            }
        }
    }
}