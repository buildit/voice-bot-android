package com.buildit.mark.android.di.module

import android.app.Application
import androidx.room.Room
import android.content.Context
import com.buildit.mark.android.BuildConfig
import com.buildit.mark.android.data.database.AppDatabase
import com.buildit.mark.android.data.network.ApiHeader
import com.buildit.mark.android.data.network.ApiHelper
import com.buildit.mark.android.data.network.AppApiHelper
import com.buildit.mark.android.data.preferences.AppPreferenceHelper
import com.buildit.mark.android.data.preferences.PreferenceHelper
import com.buildit.mark.android.di.ApiKeyInfo
import com.buildit.mark.android.di.PreferenceInfo
import com.buildit.mark.android.util.AppConstants
import com.buildit.mark.android.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by harshit.laddha on 25/01/2020
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.APP_DB_NAME).build()

    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper

    @Provides
    @Singleton
    internal fun provideProtectedApiHeader(@ApiKeyInfo apiKey: String, preferenceHelper: PreferenceHelper)
            : ApiHeader.ProtectedApiHeader = ApiHeader.ProtectedApiHeader(apiKey = apiKey)

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()


}