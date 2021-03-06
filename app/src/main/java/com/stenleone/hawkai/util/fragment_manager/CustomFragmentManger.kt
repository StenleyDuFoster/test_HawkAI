package com.stenleone.hawkai.util.fragment_manager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.stenleone.hawkai.R

class CustomFragmentManger(val activity: AppCompatActivity) {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransition: FragmentTransaction
    private val containerId = R.id.fragmentContainer

    fun addWithBackStackFragmentToFragmentManager(fragment: Fragment) {

        if (!fragment.isAdded) {
            initFragmentTransition()
            fragmentTransition.add(containerId, fragment)
            fragmentTransition.addToBackStack(null)
            fragmentTransition.commit()
        }
    }

    fun addFragmentToFragmentManager(fragment: Fragment) {

        initFragmentTransition()
        fragmentTransition.add(containerId, fragment)
        fragmentTransition.commit()
    }

    fun removeFragmentFromTransition(fragment: Fragment) {
        initFragmentTransition()
        fragmentTransition.remove(fragment)
        fragmentTransition.commit()

    }

    fun checkLastFragmentId() : String? {


            return fragmentManager.fragments[fragmentManager.fragments.size - 1].tag

    }

    private fun initFragmentTransition() {

        fragmentManager = activity.supportFragmentManager
        fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.setCustomAnimations(
            R.anim.in_leaft_to_right, R.anim.out_leaft_to_right,
            R.anim.in_leaft_to_right, R.anim.out_leaft_to_right
        )
    }

    fun clearBackStack() {
        activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}