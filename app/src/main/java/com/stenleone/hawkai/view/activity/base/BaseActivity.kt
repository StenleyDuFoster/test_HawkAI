package com.stenleone.hawkai.view.activity.base

import android.app.Activity
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.stenleone.hawkai.receiver.NetworkChangeReceiver
import com.stenleone.hawkai.util.anim.LoadLeyAnimator
import com.stenleone.hawkai.util.fragmentManager.CustomFragmentManger
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.load_lay.*

abstract class BaseActivity(private val layId: Int) : AppCompatActivity() {

    private val networkReceiver = NetworkChangeReceiver()
    val fragmentManager = CustomFragmentManger(this)
    lateinit var disposable: CompositeDisposable
    lateinit var loadLayAnim: LoadLeyAnimator

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initNetworkReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, intentFilter)
    }

    open fun initAfterCreate() {
        disposable = CompositeDisposable()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (load_bc != null && load_ico != null) {
            loadLayAnim = LoadLeyAnimator(load_ico, load_bc)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNetworkReceiver()
        setContentView(layId)
        initAfterCreate()
    }

    override fun onDestroy() {
        if(!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onDestroy()
        unregisterReceiver(networkReceiver)
        clearFindViewByIdCache()
    }
}