package com.globits.nimpe

import android.app.Application
import android.content.Context
import com.globits.nimpe.di.DaggerNimpeComponent
import com.globits.nimpe.di.NimpeComponent
import timber.log.Timber


open class NimpeApplication : Application() {
    val nimpeComponent: NimpeComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): NimpeComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerNimpeComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        //nimpeComponent = DaggerNimpeComponent.factory().create(this)
        nimpeComponent.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

