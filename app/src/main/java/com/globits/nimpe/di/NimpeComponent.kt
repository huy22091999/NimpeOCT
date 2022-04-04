package com.globits.nimpe.di

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.globits.nimpe.NimpeApplication
import com.globits.nimpe.ui.MainActivity
import com.globits.nimpe.ui.security.LoginActivity
import com.globits.nimpe.ui.security.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ViewModelModule::class,
    FragmentModule::class,
    NetWorkModule::class
])
@Singleton
interface NimpeComponent {
    fun inject(nimpeApplication: NimpeApplication)
    fun inject(activity: MainActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: SplashActivity)
    fun fragmentFactory(): FragmentFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): NimpeComponent
    }
}