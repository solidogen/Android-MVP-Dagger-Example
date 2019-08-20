package com.spyrdonapps.mvpexample.di.modules

import com.spyrdonapps.mvpexample.di.scopes.ActivityScope
import com.spyrdonapps.mvpexample.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, PresenterBuildersModule::class])
    abstract fun mainActivity(): MainActivity
}