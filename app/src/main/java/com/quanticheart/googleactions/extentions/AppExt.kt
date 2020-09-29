package com.quanticheart.googleactions.extentions

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startFeature() = this.startActivity(Intent(this, T::class.java))
inline fun <reified T> Context.getFeature() = Intent(this, T::class.java)
