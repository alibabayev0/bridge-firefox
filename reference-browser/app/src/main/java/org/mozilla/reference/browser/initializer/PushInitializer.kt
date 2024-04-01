/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.reference.browser.initializer

import android.content.Context
import androidx.annotation.Keep
import androidx.startup.Initializer
import mozilla.components.concept.push.PushProcessor
import mozilla.components.support.base.log.logger.Logger
import org.mozilla.reference.browser.BrowserApplication
import org.mozilla.reference.browser.push.PushFxaIntegration
import org.mozilla.reference.browser.push.WebPushEngineIntegration

@Keep
class PushInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val application = context as BrowserApplication
        val components = application.components

        components.push.feature?.let {
            Logger.info("AutoPushFeature is configured, initializing it...")

            PushProcessor.install(it)

            // WebPush integration to observe and deliver push messages to engine.
            WebPushEngineIntegration(components.core.engine, it).start()

            // Perform a one-time initialization of the account manager if a message is received.
            PushFxaIntegration(it, lazy { components.backgroundServices.accountManager }).launch()

            // Initialize the push feature and service.
            it.initialize()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}