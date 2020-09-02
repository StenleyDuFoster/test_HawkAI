package com.stenleone.hawkai.util.fragmentManager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.stenleone.hawkai.R

class CustomFragmentManger(val activity: AppCompatActivity) {

    lateinit var fragmentTransition: FragmentTransaction
    val containerId = R.id.fragmentContainer

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

    fun initFragmentTransition() {

        fragmentTransition = activity.supportFragmentManager.beginTransaction()
        fragmentTransition.setCustomAnimations(
            R.anim.in_leaft_to_right, R.anim.out_leaft_to_right,
            R.anim.in_leaft_to_right, R.anim.out_leaft_to_right
        )
    }

    fun clearBackStack() {
        activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}