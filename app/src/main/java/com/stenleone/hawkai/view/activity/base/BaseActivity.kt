package com.stenleone.hawkai.view.activity.base

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import com.stenleone.hawkai.util.anim.LoadLeyAnimator

import com.stenleone.hawkai.util.fragmentManager.CustomFragmentManger
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.*
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

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroy() {
        if(!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onDestroy()
        clearFindViewByIdCache()
    }
}