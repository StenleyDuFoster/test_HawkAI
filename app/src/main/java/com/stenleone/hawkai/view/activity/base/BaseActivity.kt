package com.stenleone.hawkai.view.activity.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stenleone.hawkai.receiver.NetworkChangeReceiver
import com.stenleone.hawkai.util.anim.LoadLeyAnimator
import com.stenleone.hawkai.util.extensions.initReceiver
import com.stenleone.hawkai.util.fragment_manager.CustomFragmentManger
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.load_lay.*

abstract class BaseActivity(private val layId: Int) : AppCompatActivity() {

    private val networkReceiver = NetworkChangeReceiver()
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

        initReceiver(networkReceiver)
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