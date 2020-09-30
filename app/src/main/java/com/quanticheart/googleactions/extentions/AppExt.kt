package com.quanticheart.googleactions.extentions

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startFeature() {
    val i = Intent(this, T::class.java)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    this.startActivity(i)
}

inline fun <reified T> Context.getFeature() = Intent(this, T::class.java)
