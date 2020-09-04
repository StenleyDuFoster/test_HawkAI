package com.stenleone.hawkai.view.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.R
import com.stenleone.hawkai.service.TestService
import com.stenleone.hawkai.util.constant.IntentConstant
import com.stenleone.hawkai.util.easyInfo.makeToast
import com.stenleone.hawkai.view.activity.base.BaseActivity
import com.stenleone.hawkai.view.fragment.additionals.CommentsFragment
import com.stenleone.hawkai.view.fragment.main.NewsFeedFragment
import com.stenleone.hawkai.view.fragment.main.SearchFragment
import com.stenleone.hawkai.view.fragment.main.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(R.layout.activity_main) {

    var commentsFragment: CommentsFragment? = null
    private val searchFragment = SearchFragment()
    private val settingsFragment = SettingsFragment()

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
                .subscribe {
                    onBackPressed()
                }
        )
    }

    private fun initBottomNavigation() {
        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    fragmentManager.clearBackStack()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_photo -> {
                    makeToast(getString(R.string.develop))
                }
                R.id.navigation_search -> {
                    fragmentManager.addWithBackStackFragmentToFragmentManager(searchFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    fragmentManager.addWithBackStackFragmentToFragmentManager(settingsFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun changeManySelectedItem() {

        when (fragmentManager.checkLastFragmentId()) {

            searchFragment.tag -> {
                navigationView.menu.getItem(2).isChecked = true
            }
            settingsFragment.tag -> {
                navigationView.menu.getItem(3).isChecked = true
            }
            else -> {
                navigationView.menu.getItem(0).isChecked = true
            }
        }
    }

    override fun initAfterCreate() {
        super.initAfterCreate()
        initBottomNavigation()
        initBackClick()
        fragmentManager.addFragmentToFragmentManager(NewsFeedFragment())

        //startService((Intent(this,TestService::class.java)))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backClick.visibility = View.INVISIBLE
        navigationView.visibility = View.VISIBLE
        changeManySelectedItem()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            IntentConstant.CAMERA -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    commentsFragment.let {
                        it?.intentMediaManager?.createCameraWithPermission()
                    }
                } else {
                    makeToast(getString(R.string.not_access_camera))
                }
            }
        }
    }
}