/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.reference.browser.initializer

import android.content.Context
import androidx.annotation.Keep
import androidx.startup.Initializer
import org.mozilla.reference.browser.BrowserApplication
import org.mozilla.reference.browser.ext.isCrashReportActive

@Keep
class CrashlyticsInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val application = context as BrowserApplication
        val components = application.components

        if (isCrashReportActive) {
                components
                .analytics
                .crashReporter.install(application)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}