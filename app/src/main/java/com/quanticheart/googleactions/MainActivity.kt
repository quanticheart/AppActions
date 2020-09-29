package com.quanticheart.googleactions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.googleactions.sliceActions.handleActionIntent
import com.quanticheart.googleactions.sliceActions.launchFeature
import kotlinx.android.synthetic.main.activity_app_actions.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_actions)

        feature1.setOnClickListener { launchFeature(resources.getString(R.string.feature_one)) }
        feature2.setOnClickListener { launchFeature(resources.getString(R.string.feature_two)) }
        feature3.setOnClickListener { launchFeature(resources.getString(R.string.feature_three)) }
        feature3.setOnClickListener { launchFeature(resources.getString(R.string.feature_four)) }
        handleActionIntent()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleActionIntent()
    }
}
