package com.nab.phuong.weather.device

import android.content.Context
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DeviceModule {
    @Provides
    fun provideRootBeer(context: Context): RootBeer {
        return RootBeer(context)
    }

    @Provides
    @Singleton
    fun providesDeviceInfo(rootBeer: RootBeer): DeviceInfo {
        return DeviceInfo(rooted = rootBeer.isRooted)
    }
}
