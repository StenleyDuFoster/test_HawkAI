package com.stenleone.hawkai.view.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(val layId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layId)
    }
}