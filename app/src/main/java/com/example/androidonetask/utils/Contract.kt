package com.example.androidonetask.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.androidonetask.activity.ArtActivity

class Contract : ActivityResultContract<CharSequence?, CharSequence?>() {

    override fun createIntent(context: Context, input: CharSequence?): Intent {
        return Intent(context, ArtActivity::class.java)
            .putExtra("message", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): CharSequence? {
        intent ?: return null
        if (resultCode != Activity.RESULT_OK) return null

        return intent.getCharSequenceExtra("message")
    }

    override fun getSynchronousResult(
        context: Context,
        input: CharSequence?
    ): SynchronousResult<CharSequence?>? {
        return null
    }
}