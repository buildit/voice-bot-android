package com.buildit.mark.android.ui.login.view

import android.content.Intent
import android.os.Bundle
import com.buildit.mark.android.R
import com.buildit.mark.android.ui.base.view.BaseActivity
import com.buildit.mark.android.ui.login.interactor.LoginMVPInteractor
import com.buildit.mark.android.ui.login.presenter.LoginMVPPresenter
import com.buildit.mark.android.ui.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * Created by harshit.laddha on 25/01/2020
 */
class LoginActivity : BaseActivity(), LoginMVPView {

    @Inject
    internal lateinit var presenter: LoginMVPPresenter<LoginMVPView, LoginMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onAttach(this)
        setOnClickListeners()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun onFragmentAttached() {
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setOnClickListeners() {
        btnServerLogin.setOnClickListener { openMainActivity() }
        ibGoogleLogin.setOnClickListener { openMainActivity() }
        ibFbLogin.setOnClickListener { openMainActivity() }
    }
}