package com.quanticheart.googleactions.activity

import com.quanticheart.googleactions.R
import com.quanticheart.googleactions.activity.base.BaseActivity

class FeatureThreeActivity : BaseActivity() {
    override fun setLayout(): Int = R.layout.feature_activity

    override fun setTitle(): String = resources.getString(R.string.title_feature_3)

    override fun setSubTitle(): String = resources.getString(R.string.subtitle_feature_3)

    override fun setImage(): Int = R.drawable.image_leaf
}
