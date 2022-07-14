package com.hangrycoder.contactspro.utils

import android.view.View
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun View.delayOnLifecycle(
    durationInMillis: Long,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    block: () -> Unit
) {
    findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
        lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher) {
            delay(durationInMillis)
            block()
        }
    }
}