package com.nab.phuong.feature_forecast.utils

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Why this is needed?
 * To solve : java.lang.NullPointerException
 * at androidx.arch.core.executor.DefaultTaskExecutor.isMainThread(DefaultTaskExecutor.java)
 * ---because JVM unit tests don’t know anything about the Android main thread!
 *  Hence all unit test are executed on a random (background) thread
 *
 * InstantTaskExecutorRule doesn't work on JUnit 5 as the concept of Rule and TestRunner
 * are merged into one single concept of Extensions
 *
 * This does two things
 * 1. set a delegate before each test that updates live data values immediately on the calling thread
 * 2. remove the delegate after each tests to avoid influencing other tests.
 */
@SuppressLint("RestrictedApi")
class InstantExecutorExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
    }


    override fun afterEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}
