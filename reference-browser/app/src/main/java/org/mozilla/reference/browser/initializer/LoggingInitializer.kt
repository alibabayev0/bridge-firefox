/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.reference.browser.initializer

import android.content.Context
import androidx.annotation.Keep
import androidx.startup.Initializer
import mozilla.components.support.base.log.Log
import mozilla.components.support.base.log.sink.AndroidLogSink
import mozilla.components.support.rustlog.RustLog

@Keep
class LoggingInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Log.addSink(AndroidLogSink())
        RustLog.enable()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}