package com.buildit.mark.android.di.builder

import com.buildit.mark.android.ui.login.LoginActivityModule
import com.buildit.mark.android.ui.login.view.LoginActivity
import com.buildit.mark.android.ui.main.bills.BillsFragmentProvider
import com.buildit.mark.android.ui.main.chat.ChatFragmentProvider
import com.buildit.mark.android.ui.main.view.MainActivity
import com.buildit.mark.android.ui.splash.SplashActivityModule
import com.buildit.mark.android.ui.splash.view.SplashMVPActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashMVPActivity

    @ContributesAndroidInjector(modules = [(BillsFragmentProvider::class), (ChatFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

}