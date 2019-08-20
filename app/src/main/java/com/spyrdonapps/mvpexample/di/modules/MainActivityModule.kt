package com.spyrdonapps.mvpexample.di.modules

import android.app.Activity
import com.spyrdonapps.mvpexample.di.scopes.ActivityScope
import com.spyrdonapps.mvpexample.ui.MainActivity
import com.spyrdonapps.mvpexample.ui.MainContract
import com.spyrdonapps.mvpexample.ui.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun searchActivity(activity: MainActivity): Activity
}