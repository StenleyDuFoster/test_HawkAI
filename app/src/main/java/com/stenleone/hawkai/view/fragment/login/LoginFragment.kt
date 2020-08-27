package com.stenleone.hawkai.view.fragment.login

import com.jakewharton.rxbinding3.view.clicks

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.view_model.LoginViewModel
import com.stenleone.hawkai.view.activity.LoginActivity
import com.stenleone.hawkai.view.fragment.base.BaseFragment

import kotlinx.android.synthetic.main.fragment_login.*

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment(R.layout.fragment_login), KoinComponent {

    private val viewModel: LoginViewModel by inject()

    private fun initButton() {

        buttonSignIn.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {
                animLoader(true)
                viewModel.loginHawkAI(codeEditText.text.toString())
            }
    }

    private fun stuDaInitPass() { //debug code !!DELETE!!
            codeEditText.setText("222222")
    }

    override fun animLoader(isAnimate: Boolean) {
        buttonSignIn.isClickable = !isAnimate
        codeEditText.isClickable = !isAnimate
        super.animLoader(isAnimate)
    }

    override fun initAfterViewCreated() {
        initButton()
        stuDaInitPass()
    }

    override fun initViewModelCallBack() {

        viewModel.apply {
            liveData.observe(viewLifecycleOwner, {
                activity.let {
                    (it as LoginActivity).loadMainActivity()
                    animLoader(false)
                }
            })

            liveError.observe(viewLifecycleOwner, {
                titleText.setText(getString(R.string.error_msg))
                subtitleText.setText("")
                animLoader(false)
            })
        }
    }
}