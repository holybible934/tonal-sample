package com.tonal.interview

import android.app.Application

class TonalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyInjection.application = this
    }
}