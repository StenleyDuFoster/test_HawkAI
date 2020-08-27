package com.stenleone.hawkai.view.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stenleone.hawkai.R
import com.stenleone.hawkai.view.activity.base.BaseActivity

abstract class BaseFragment(val layId: Int) : Fragment() {

    abstract fun initAfterViewCreated()
    open fun initViewModelCallBack() { }

    open protected fun animLoader(isAnimate: Boolean) {

        activity.let {
            (it as BaseActivity).apply {
                if(isAnimate) {
                    this.loadLayAnim.loadAnim()
                } else {
                    this.loadLayAnim.fadeOut()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAfterViewCreated()
        initViewModelCallBack()
        super.onViewCreated(view, savedInstanceState)
    }
}