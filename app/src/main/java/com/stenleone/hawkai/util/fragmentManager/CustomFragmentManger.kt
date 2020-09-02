package com.stenleone.hawkai.util.fragmentManager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.stenleone.hawkai.R
import com.stenleone.hawkai.view.activity.base.BaseActivity

class CustomFragmentManger(val activity: BaseActivity) {

    lateinit var fragmentTransition: FragmentTransaction

    fun addWithBackStackFragmentToFragmentManager(fragment: Fragment) {

        initFragmentTransition()
        fragmentTransition.add(R.id.fragmentContainer, fragment)
        fragmentTransition.addToBackStack(null)
        fragmentTransition.commit()
    }

    fun addFragmentToFragmentManager(fragment: Fragment) {

        initFragmentTransition()
        fragmentTransition.add(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }

    fun initFragmentTransition() {

        fragmentTransition = activity.supportFragmentManager.beginTransaction()
        fragmentTransition.setCustomAnimations(
            R.anim.in_leaft_to_right, R.anim.out_leaft_to_right,
            R.anim.in_leaft_to_right, R.anim.out_leaft_to_right
        )
    }

}