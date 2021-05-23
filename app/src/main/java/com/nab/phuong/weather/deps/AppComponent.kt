package com.nab.phuong.weather.deps

import android.content.Context
import com.nab.phuong.weather.presentation.AppPresentationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppPresentationModule::class])
@Singleton
interface AppComponent : AppComponentDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
