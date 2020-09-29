package com.quanticheart.googleactions.sliceActions

import android.app.Activity
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.service.voice.VoiceInteractionService
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.SliceManager
import androidx.slice.builders.*
import com.quanticheart.googleactions.R
import com.quanticheart.googleactions.activity.FeatureFourActivity
import com.quanticheart.googleactions.activity.FeatureOneActivity
import com.quanticheart.googleactions.activity.FeatureThreeActivity
import com.quanticheart.googleactions.activity.FeatureTwoActivity
import com.quanticheart.googleactions.extentions.startFeature
import java.util.*

/**
 * Const
 */

const val DEFAULT_TITLE = "URI not found."
const val DEFAULT_ACTION_TITLE = "Open App"
const val QUERY_PARAMETER_NAME = "featureName"

/**
 * Slice PendingIntent Action
 */

fun Context.buildActivityAction(intent: Intent): SliceAction? = SliceAction.create(
    PendingIntent.getActivity(this, 0, intent, 0),
    IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground),
    ListBuilder.ICON_IMAGE,
    DEFAULT_ACTION_TITLE
)

/**
 * Create Slice
 */

fun Context.buildDefaultSlice(sliceUri: Uri, action: SliceAction) =
    list(this, sliceUri, ListBuilder.INFINITY) {
        row {
            title = resources.getString(R.string.app_name)
            subtitle = resources.getString(R.string.click_to_open_app)
            primaryAction = action
        }
    }

fun Context.buildFeatureSlice(
    sliceUri: Uri,
    action: SliceAction,
    @StringRes titleRes: Int,
    @StringRes subtitleRes: Int,
    @DrawableRes imageRes: Int
) = list(this, sliceUri, ListBuilder.INFINITY) {
    row {
        title = resources.getString(titleRes).toUpperCase(Locale.getDefault())
        subtitle = resources.getString(subtitleRes)
        primaryAction = action
    }
    gridRow {
        cell {
            addImage(
                IconCompat.createWithResource(this@buildFeatureSlice, imageRes),
                ListBuilder.LARGE_IMAGE
            )
        }
    }
}

/**
 * Slice Permissions
 */
fun Context.grantSlicePermissions() {
    val sliceProviderUri = Uri.Builder().apply {
        scheme(ContentResolver.SCHEME_CONTENT)
        authority(applicationContext.packageName)
    }.build()

    val assistantPackage = applicationContext.packageManager.queryIntentServices(
        Intent(VoiceInteractionService.SERVICE_INTERFACE), 0
    )?.let { it[0].serviceInfo.packageName } ?: return

    SliceManager.getInstance(applicationContext)
        .grantSlicePermission(assistantPackage, sliceProviderUri)
}

/**
 * Handles the action from the intent base on the type.
 *
 * @receiver the intent to handle
 */
fun Activity.handleActionIntent() {
    val appLinkAction = intent.action
    val appLinkData: Uri? = intent.data
    if (Intent.ACTION_VIEW == appLinkAction) {
        appLinkData?.getQueryParameter(QUERY_PARAMETER_NAME)?.let {
            launchFeature(it)
        }
    }
}

fun Context.launchFeature(feature: String) {
    when (feature) {
        resources.getString(R.string.feature_one) -> startFeature<FeatureOneActivity>()
        resources.getString(R.string.feature_two) -> startFeature<FeatureTwoActivity>()
        resources.getString(R.string.feature_three) -> startFeature<FeatureThreeActivity>()
        resources.getString(R.string.feature_four) -> startFeature<FeatureFourActivity>()
    }
}