package com.spyrdonapps.mvpexample.di.components

import com.spyrdonapps.mvpexample.MyApp
import com.spyrdonapps.mvpexample.di.modules.ActivityBuildersModule
import com.spyrdonapps.mvpexample.di.modules.NetworkModule
import com.spyrdonapps.mvpexample.di.modules.PresenterBuildersModule
import com.spyrdonapps.mvpexample.di.scopes.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ActivityBuildersModule::class/*,
        PresenterBuildersModule::class*/
    ]
)
interface MyAppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>()
}