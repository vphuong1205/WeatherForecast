package com.nab.phuong.weather.home.deps

import android.content.Context
import com.nab.phuong.weather.home.data.HomeDataModule
import com.nab.phuong.weather.home.domain.HomeDomainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [HomeDomainModule::class, HomeDataModule::class])
@Singleton
interface HomeComponent : HomeComponentDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): HomeComponent
    }
}
