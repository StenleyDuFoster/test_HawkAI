package com.stenleone.hawkai.view.activity.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.util.anim.LoadLeyAnimator

import com.stenleone.hawkai.util.fragmentManager.CustomFragmentManger
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.load_lay.*
import java.util.concurrent.TimeUnit

abstract class BaseActivity(val layId: Int) : AppCompatActivity() {

    val fragmentManager = CustomFragmentManger(this)
    lateinit var disposable: CompositeDisposable
    lateinit var loadLayAnim: LoadLeyAnimator

    open fun initAfterCreate() {
        disposable = CompositeDisposable()
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

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}