package com.quanticheart.googleactions.sliceActions

import android.annotation.SuppressLint
import android.net.Uri
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.list
import androidx.slice.builders.row
import com.quanticheart.googleactions.MainActivity
import com.quanticheart.googleactions.R
import com.quanticheart.googleactions.activity.FeatureOneActivity
import com.quanticheart.googleactions.activity.FeatureThreeActivity
import com.quanticheart.googleactions.activity.FeatureTwoActivity
import com.quanticheart.googleactions.extentions.getFeature

@SuppressLint("Slices")
class AppActionsSliceProvider : SliceProvider() {

    override fun onCreateSliceProvider() = true

    override fun onBindSlice(sliceUri: Uri): Slice? {
        return context?.let { context ->
            when (sliceUri.path) {
                "/" -> context.buildActivityAction(context.getFeature<MainActivity>())?.let {
                    context.buildDefaultSlice(sliceUri, it)
                }
                "/${context.resources.getString(R.string.feature_one)}" -> context.buildActivityAction(
                    context.getFeature<FeatureOneActivity>()
                )
                    ?.let {
                        context.buildFeatureSlice(
                            sliceUri,
                            it,
                            R.string.title_feature_1,
                            R.string.subtitle_feature_1,
                            R.drawable.image_aurora
                        )
                    }
                "/${context.resources.getString(R.string.feature_two)}" -> context.buildActivityAction(
                    context.getFeature<FeatureTwoActivity>()
                )
                    ?.let {
                        context.buildFeatureSlice(
                            sliceUri,
                            it,
                            R.string.title_feature_2,
                            R.string.subtitle_feature_2,
                            R.drawable.image_flower
                        )
                    }
                "/${context.resources.getString(R.string.feature_three)}" -> context.buildActivityAction(
                    context.getFeature<FeatureThreeActivity>()
                )
                    ?.let {
                        context.buildFeatureSlice(
                            sliceUri,
                            it,
                            R.string.title_feature_3,
                            R.string.subtitle_feature_3,
                            R.drawable.image_leaf
                        )
                    }
                else -> list(context, sliceUri, ListBuilder.INFINITY) {
                    row {
                        title =
                            DEFAULT_TITLE
                    }
                }
            }
        }
    }

    override fun onSlicePinned(sliceUri: Uri?) {}

    override fun onSliceUnpinned(sliceUri: Uri?) {}
}
