package com.spyrdonapps.mvpexample.di.modules

import com.spyrdonapps.mvpexample.data.remote.ApiService
import com.spyrdonapps.mvpexample.ui.MainContract
import com.spyrdonapps.mvpexample.ui.MainPresenter
import dagger.Module
import dagger.Provides

@Module
abstract class PresenterBuildersModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideMainPresenter(apiService: ApiService): MainContract.Presenter = MainPresenter(apiService)
    }
}