package com.quanticheart.googleactions.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.feature_activity.*

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setLayout(): Int
    abstract fun setTitle(): String
    abstract fun setSubTitle(): String
    abstract fun setImage(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        titleTextView.text = setTitle()
        subTitleTextView.text = setSubTitle()
        imageView.setImageResource(setImage())
    }
}
