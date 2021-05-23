package com.nab.phuong.weather.home.data

import android.content.Context
import com.nab.phuong.weather.home.data.repository.HomeRepositoryImpl
import com.nab.phuong.weather.home.domain.repository.HomeRepository
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeDataModule {

    @Provides
    @Singleton
    fun provideRootBeer(context: Context): RootBeer {
        return RootBeer(context)
    }

    @Provides
    @Singleton
    fun providesHomeRepository(
        rootBeer: RootBeer
    ): HomeRepository {
        return HomeRepositoryImpl(rootBeer)
    }
}
