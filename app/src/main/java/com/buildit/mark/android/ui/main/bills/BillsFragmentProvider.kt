package com.buildit.mark.android.ui.main.bills

import com.buildit.mark.android.ui.main.bills.view.BillsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
internal abstract class BillsFragmentProvider {

    @ContributesAndroidInjector(modules = [BillsFragmentModule::class])
    internal abstract fun provideBlogFragmentFactory(): BillsFragment
}