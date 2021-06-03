package com.nab.phuong.weather.home.data

import android.app.Application
import com.nab.phuong.weather.home.data.repository.HomeRepositoryImpl
import com.nab.phuong.weather.home.domain.repository.HomeRepository
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeDataModule {

    @Provides
    @Singleton
    fun provideRootBeer(application: Application): RootBeer {
        return RootBeer(application)
    }

    @Provides
    @Singleton
    fun providesHomeRepository(
        rootBeer: RootBeer
    ): HomeRepository {
        return HomeRepositoryImpl(rootBeer)
    }
}
