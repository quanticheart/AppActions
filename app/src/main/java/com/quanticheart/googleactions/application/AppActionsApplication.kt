package com.quanticheart.googleactions.application

import android.app.Application
import com.quanticheart.googleactions.sliceActions.grantSlicePermissions

@Suppress("unused")
class AppActionsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        grantSlicePermissions()
    }
}
