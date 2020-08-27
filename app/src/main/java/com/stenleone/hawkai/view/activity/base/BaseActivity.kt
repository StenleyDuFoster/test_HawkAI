package com.stenleone.hawkai.view.activity.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stenleone.hawkai.util.anim.LoadLeyAnimator

import com.stenleone.hawkai.util.fragmentManager.CustomFragmentManger
import kotlinx.android.synthetic.main.load_lay.*

abstract class BaseActivity(val layId: Int) : AppCompatActivity() {

    protected val fragmentManager = CustomFragmentManger(this)
    lateinit var loadLayAnim: LoadLeyAnimator

    open fun initAfterCreate() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (load_bc != null && load_ico != null) {
            loadLayAnim = LoadLeyAnimator(load_ico, load_bc)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layId)
        initAfterCreate()
    }
}