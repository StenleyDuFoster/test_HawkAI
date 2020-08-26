package com.stenleone.hawkai.view.fragment

import com.jakewharton.rxbinding3.view.clicks

import com.stenleone.hawkai.R
import com.stenleone.hawkai.model.view_model.LoginViewModel
import com.stenleone.hawkai.util.easyToast.makeToast
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
                viewModel.loginHawkAI(codeEditText.text.toString())
            }
    }

    override fun initAfterViewCreated() {
        initButton()
    }

    override fun viewModelCallBack() {

        viewModel.apply {
            liveData.observe(viewLifecycleOwner, {
                makeToast(it.toString())
            })

            liveError.observe(viewLifecycleOwner, {
                makeToast(it)
            })
        }
    }
}