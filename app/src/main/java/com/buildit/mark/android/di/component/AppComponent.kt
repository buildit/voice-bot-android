package com.buildit.mark.android.di.component

import android.app.Application
import com.buildit.mark.android.MvpApp
import com.buildit.mark.android.di.builder.ActivityBuilder
import com.buildit.mark.android.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)

}