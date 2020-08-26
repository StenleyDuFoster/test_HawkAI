package com.stenleone.hawkai.view.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.stenleone.hawkai.util.fragmentManager.CustomFragmentManger

abstract class BaseActivity(val layId: Int) : AppCompatActivity() {

   protected val fragmentManager = CustomFragmentManger(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layId)
        initAfterCreate()
    }

    abstract fun initAfterCreate()
}